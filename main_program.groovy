import jp.crestmuse.cmx.inference.*
import jp.crestmuse.cmx.filewrappers.*
import jp.crestmuse.cmx.amusaj.sp.*;

Map spn2MidiNote = new LinkedHashMap()
spn2MidiNote.put("F2",   41)
spn2MidiNote.put("F2#",  42)
spn2MidiNote.put("G2",   43)
spn2MidiNote.put("G2#",  44)
spn2MidiNote.put("A2",   45)
spn2MidiNote.put("A2#",  46)
spn2MidiNote.put("B2",   47)
spn2MidiNote.put("C3",   48)
spn2MidiNote.put("C3#",  49)
spn2MidiNote.put("D3",   50)
spn2MidiNote.put("D3#",  51)
spn2MidiNote.put("E3",   52)
spn2MidiNote.put("F3",   53)
spn2MidiNote.put("F3#",  54)
spn2MidiNote.put("G3",   55)
spn2MidiNote.put("G3#",  56)
spn2MidiNote.put("A3",   57)
spn2MidiNote.put("A3#",  58)
spn2MidiNote.put("B3",   59)
spn2MidiNote.put("C4",   60)
spn2MidiNote.put("C4#",  61)
spn2MidiNote.put("D4",   62)
spn2MidiNote.put("D4#",  63)
spn2MidiNote.put("E4",   64)
spn2MidiNote.put("F4",   65)
spn2MidiNote.put("F4#",  66)
spn2MidiNote.put("G4",   67)
spn2MidiNote.put("G4#",  68)
spn2MidiNote.put("A4",   69)
spn2MidiNote.put("A4#",  70)
spn2MidiNote.put("B4",   71)
spn2MidiNote.put("C5",   72)
spn2MidiNote.put("C5#",  73)
spn2MidiNote.put("D5",   74)
spn2MidiNote.put("D5#",  75)
spn2MidiNote.put("E5",   76)
spn2MidiNote.put("F5",   77)
spn2MidiNote.put("F5#",  78)
spn2MidiNote.put("G5",   79)
spn2MidiNote.put("G5#",  80)
spn2MidiNote.put("A5",   81)

Map midiNote2Spn = new LinkedHashMap()
def entrySet = spn2MidiNote.entrySet()
entrySet.each { entry ->
  midiNote2Spn.put(entry.getValue(), entry.getKey())
}

def mr = MusicRepresentationFactory.create(32,32)
String[] valuesS = ["C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "A4#", "B4", "C5", "C5#", "D5", "D5#", "E5", "F5", "F5#", "G5", "G5#", "A5", "0"]

String[] valuesA = ["G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "A4#", "B4", "C5", "C5#", "D5", "D5#", "E5", "0"]

String[] valuesT = ["C3", "C3#", "D3", "D3#", "E3", "F3", "F3#", "G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "0"]

String[] valuesB = ["F2", "F2#", "G2", "G2#", "A2", "A2#", "B2", "C3", "C3#", "D3", "D3#", "E3", "F3", "F3#", "G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "0"]

String[] valuesC = ["C","Cm","C#","C#m","D","Dm","D#","D#m","E","Em","E#","E#m","F","Fm","F#","F#m","G","Gm","G#","G#m","A","Am","A#","A#m",
"B","Bm","B#","B#m", "0"]

mr.addMusicLayer("s", valuesS)
mr.addMusicLayer("a", valuesA)
mr.addMusicLayer("t", valuesT)
mr.addMusicLayer("b", valuesB)
mr.addMusicLayer("c", valuesC)

def bn = new BayesNetWrapper(args[1])
def bc = new BayesianCalculator(bn)
bc.addReadMapping(new BayesianMapping("s", 0, 0, "s", bn))
bc.addReadMapping(new BayesianMapping("s", 0, 0, "s1", bn))
bc.addReadMapping(new BayesianMapping("s", 1, 0, "s2", bn))
bc.addReadMapping(new BayesianMapping("a", -1, 0, "a", bn))
bc.addReadMapping(new BayesianMapping("t", -1, 0, "t", bn))
bc.addReadMapping(new BayesianMapping("b", -1, 0, "b", bn))
bc.addReadMapping(new BayesianMapping("c", -1, 0, "c", bn))

bc.addWriteMapping(new BayesianMapping("a", 0, 0, "a1", bn))
bc.addWriteMapping(new BayesianMapping("t", 0, 0, "t1", bn))
bc.addWriteMapping(new BayesianMapping("b", 0, 0, "b1", bn))

mr.addMusicCalculator("s", bc)

def c = 0
def c1 = 0
def c2 = 0
def c3 = 0
def m = 0
def n = 0
def chordlist=[]

SCCXMLWrapper scc = CMXFileWrapper.readfile(args[0])
//SCCXMLWrapper scc = MIDIXMLWrapper.readSMF(args[0]).toSCCXML();
partlist = scc.getPartList()
notelist = partlist[0].getNoteOnlyList()
l = notelist.length
/*println("音符の数 = " + l)*/

SCCDataSet scc2 = new SCCDataSet(480)
scc2.addHeaderElement(0, "TEMPO", "120")

SCC.Part partS = scc2.addPart(1, 1, 0, 100, "Soprano");
SCC.Part partA = scc2.addPart(1, 1, 0, 100, "Alto");
SCC.Part partT = scc2.addPart(1, 1, 0, 100, "Tenor");
SCC.Part partB = scc2.addPart(1, 1, 0, 100, "Bass");

def s_0 = mr.getMusicElement("s", 0)
def a_0 = mr.getMusicElement("a", 0)
def t_0 = mr.getMusicElement("t", 0)
def b_0 = mr.getMusicElement("b", 0)

a_0.setEvidence("0")
t_0.setEvidence("0")
b_0.setEvidence("0")
s_0.setEvidence("0")

for(i=1; i<=l; i++){
  println(i + "/" + l)
  if(i==l){
    def a_last = mr.getMusicElement("a", l+1)
    def t_last = mr.getMusicElement("t", l+1)
    def b_last = mr.getMusicElement("b", l+1)

    a_last.setEvidence("0")
    t_last.setEvidence("0")
    b_last.setEvidence("0")
  }
  else{
    def ej = mr.getMusicElement("s", i+1)
    c = notelist[i].notenum()
    ej.setEvidence(ej.indexOf(midiNote2Spn.getOrDefault(c, "0")))
  }

  def ei = mr.getMusicElement("s", i)
  c = notelist[i-1].notenum()
  ei.setEvidence(ei.indexOf(midiNote2Spn.getOrDefault(c, "0")))

  def si = mr.getMusicElement("s", i)
  def ssi = si.getHighestProbIndex()
  def sssi = si.getProb(ssi)
  println(sssi)
  println("Input Soprano" + (i) + ":" +  valuesS[ssi])
  m = notelist[i-1].offset(480)
  n = notelist[i-1].onset(480)
  partS.addNoteElement(n, m, c, 100, 100)

  def ai = mr.getMusicElement("a", i)
  def aai = ai.getHighestProbIndex()
  def aaai = ai.getProb(aai)
  ai.setEvidence(aai)
  println(aaai + "Alto:" + valuesA[aai])
  c1 = spn2MidiNote.getOrDefault(valuesA[aai], 0)
  partA.addNoteElement(n, m, c1, 100, 100)

  def ti = mr.getMusicElement("t", i)
  def tti = ti.getHighestProbIndex()
  def ttti = ti.getProb(tti)
  ti.setEvidence(tti)
  println(ttti + "Tenor:" + valuesT[tti])
  c2 = spn2MidiNote.getOrDefault(valuesT[tti], 0)
  partT.addNoteElement(n, m, c2, 100, 100)

  def bi = mr.getMusicElement("b", i)
  def bbi = bi.getHighestProbIndex()
  def bbbi = bi.getProb(bbi)
  bi.setEvidence(bbi)
  println(bbbi + "Bass:" + valuesB[bbi])
  c3 = spn2MidiNote.getOrDefault(valuesB[bbi], 0)
  partB.addNoteElement(n, m, c3, 100, 100)
}
// fileobj = new File(args[0] + args[1])
scc2.toWrapper().toMIDIXML().writefileAsSMF("results/" + args[0] + args[1] + ".mid")
