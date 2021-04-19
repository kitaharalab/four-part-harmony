import jp.crestmuse.cmx.inference.*
import jp.crestmuse.cmx.filewrappers.*
import jp.crestmuse.cmx.amusaj.sp.*;

def SOPRANO = "s"
def ALTO = "a"
def TENOR = "t"
def BASS = "b"
def CODE = "c"

// MIDI note number
def midiNote = [41, 42, 43, 44, 45, 46, 47, 
                48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
                60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 
                72, 73, 74, 75, 76, 77, 78, 79, 80, 81]

def labelsS = midiNote[19..40] //C4(60) to A5(81)
def labelsA = midiNote[14..35] //G3(55) to E5(76)
def labelsT = midiNote[7..28]  //C3(48) to A4(69)
def labelsB = midiNote[0..21]  //F2(41) to D4(62)
[labelsS, labelsA, labelsT, labelsB].each { labels ->
  labels.add(0)
  println(labels)
}
def labelsC = ["C","Cm","C#","C#m","D","Dm","D#","D#m","E","Em","E#","E#m","F","Fm","F#","F#m","G","Gm","G#","G#m","A","Am","A#","A#m",
"B","Bm","B#","B#m", "0"]

def setMostLikely = { mr, part, index, onset, offset ->
  def element = mr.getMusicElement(part.name(), index)
  def mostLikely = element.getMostLikely()
  println("${part.name()}:${index}:${mostLikely}:${element.getProb(mostLikely)}")
  element.setEvidence(mr.getLabels(part.name()).contains(mostLikely) ? mostLikely : 0)
  part.addNoteElement(onset, offset, mostLikely, 100, 100)
}

def setEvidence = { mr, part, index, notenum ->
    def element = mr.getMusicElement(part.name(), index)
    println("${part.name()}:${index}:${notenum}")
    element.setEvidence(notenum)
}

def bn = new BayesNetWrapper(args[1])
def bc = new BayesianCalculator(bn)
bc.with{
  addReadMapping(new BayesianMapping(SOPRANO, 0, 0, "s", bn))
  addReadMapping(new BayesianMapping(SOPRANO, 0, 0, "s1", bn))
  addReadMapping(new BayesianMapping(SOPRANO, 1, 0, "s2", bn))
  addReadMapping(new BayesianMapping(ALTO, -1, 0, "a", bn))
  addReadMapping(new BayesianMapping(TENOR, -1, 0, "t", bn))
  addReadMapping(new BayesianMapping(BASS, -1, 0, "b", bn))
  addReadMapping(new BayesianMapping(CODE, -1, 0, "c", bn))

  addWriteMapping(new BayesianMapping(ALTO, 0, 0, "a1", bn))
  addWriteMapping(new BayesianMapping(TENOR, 0, 0, "t1", bn))
  addWriteMapping(new BayesianMapping(BASS, 0, 0, "b1", bn))
}

def mr = MusicRepresentationFactory.create(32,32)
mr.with{
  addMusicLayer(SOPRANO, labelsS)
  addMusicLayer(ALTO, labelsA)
  addMusicLayer(TENOR, labelsT)
  addMusicLayer(BASS, labelsB)
  addMusicLayer(CODE, labelsC)
  addMusicCalculator(SOPRANO, bc)
}

SCCXMLWrapper scc = CMXFileWrapper.readfile(args[0])
partlist = scc.getPartList()
notelist = partlist[0].getNoteOnlyList()
l = notelist.length

SCCDataSet scc2 = new SCCDataSet(480)
scc2.addHeaderElement(0, "TEMPO", "120")

SCC.Part partS = scc2.addPart(1, 1, 0, 100, SOPRANO);
SCC.Part partA = scc2.addPart(1, 1, 0, 100, ALTO);
SCC.Part partT = scc2.addPart(1, 1, 0, 100, TENOR);
SCC.Part partB = scc2.addPart(1, 1, 0, 100, BASS);

// process for element[0]
[partA, partT, partB, partS].each{ part -> setEvidence(mr, part, 0, 0) }
// process for elemnt[1] to notelist.length
for(i=1; i<=l; i++){
  println(i + "/" + l)
  
  if(i==l){
    [partA, partT, partB].each{ part -> setEvidence(mr, part, i+1, 0) }
  } else {
    setEvidence(mr, partS, i+1, notelist[i].notenum())  
  }

  setEvidence(mr, partS, i, notelist[i-1].notenum())

  def onset = notelist[i-1].onset(480)
  def offset = notelist[i-1].offset(480)
  partS.addNoteElement(onset, offset, notelist[i-1].notenum(), 100, 100)

  [partA, partT, partB].each{ part ->
    setMostLikely(mr, part, i, onset, offset)
  }
}
scc2.toWrapper().toMIDIXML().writefileAsSMF("results/" + args[0] + "." + args[1] + ".mid")
