import jp.crestmuse.cmx.inference.*
import jp.crestmuse.cmx.filewrappers.*
import jp.crestmuse.cmx.amusaj.sp.*;

def mr = MusicRepresentationFactory.create(32,32)
String[] values1 = ["C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "A4#", "B4", "C5", "C5#", "D5", "D5#", "E5", "F5", "F5#", "G5", "G5#", "A5", "0"]

String[] values2 = ["G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "A4#", "B4", "C5", "C5#", "D5", "D5#", "E5", "0"]

String[] values3 = ["C3", "C3#", "D3", "D3#", "E3", "F3", "F3#", "G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "D4#", "E4", "F4", "F4#", "G4", "G4#", "A4", "0"]

String[] values4 = ["F2", "F2#", "G2", "G2#", "A2", "A2#", "B2", "C3", "C3#", "D3", "D3#", "E3", "F3", "F3#", "G3", "G3#", "A3", "A3#", "B3", "C4", "C4#", "D4", "0"]

String[] values5 = ["C","Cm","C#","C#m","D","Dm","D#","D#m","E","Em","E#","E#m","F","Fm","F#","F#m","G","Gm","G#","G#m","A","Am","A#","A#m",
"B","Bm","B#","B#m", "0"]

mr.addMusicLayer("s", values1)
mr.addMusicLayer("a", values2)
mr.addMusicLayer("t", values3)
mr.addMusicLayer("b", values4)
mr.addMusicLayer("c", values5)

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

SCC.Part part1 = scc2.addPart(1, 1, 0, 100, "Soprano");
SCC.Part part2 = scc2.addPart(1, 1, 0, 100, "Alto");
SCC.Part part3 = scc2.addPart(1, 1, 0, 100, "Tenor");
SCC.Part part4 = scc2.addPart(1, 1, 0, 100, "Bass");

def s_0 = mr.getMusicElement("s", 0)
def a_0 = mr.getMusicElement("a", 0)
def t_0 = mr.getMusicElement("t", 0)
def b_0 = mr.getMusicElement("b", 0)


a_0.setEvidence("0")
t_0.setEvidence("0")
b_0.setEvidence("0")
s_0.setEvidence("0")

for(i=1;i<l+1;i++){
if(i==l){println(i)
	def a_last = mr.getMusicElement("a", l+1)
	def t_last = mr.getMusicElement("t", l+1)
	def b_last = mr.getMusicElement("b", l+1)

	a_last.setEvidence("0")
	t_last.setEvidence("0")
	b_last.setEvidence("0")
}
 j = i+1
println(j)
if(i==l){}
else{
 def ej = mr.getMusicElement("s", j)
 if(notelist[i].notenum() == 60){
  c = 60
  ej.setEvidence(ej.indexOf("C4"))
 }
 if(notelist[i].notenum() == 61){
  c = 61
  ej.setEvidence(ej.indexOf("C4#"))
 }
 if(notelist[i].notenum() == 62){
  c = 62
  ej.setEvidence(ej.indexOf("D4"))
 }
 if(notelist[i].notenum() == 63){
  c = 63
  ej.setEvidence(ej.indexOf("D4#"))
 }
 if(notelist[i].notenum() == 64){
  c = 64
  ej.setEvidence(ej.indexOf("E4"))
 }
 if(notelist[i].notenum() == 65){
  c = 65
  ej.setEvidence(ej.indexOf("F4"))
 }
 if(notelist[i].notenum() == 66){
  c = 66
  ej.setEvidence(ej.indexOf("F4#"))
 }
 if(notelist[i].notenum() == 67){
  c = 67
  ej.setEvidence(ej.indexOf("G4"))
 }
 if(notelist[i].notenum() == 68){
  c = 68
  ej.setEvidence(ej.indexOf("G4#"))
 }
 if(notelist[i].notenum() == 69){
  c = 69
  ej.setEvidence(ej.indexOf("A4"))
 }
 if(notelist[i].notenum() == 70){
  c = 70
  ej.setEvidence(ej.indexOf("A4#"))
 }
 if(notelist[i].notenum() == 71){
  c = 71
  ej.setEvidence(ej.indexOf("B4"))
 }
 if(notelist[i].notenum() == 72){
  c = 72
  ej.setEvidence(ej.indexOf("C5"))
 }
 if(notelist[i].notenum() == 73){
  c = 73
  ej.setEvidence(ej.indexOf("C5#"))
 }
 if(notelist[i].notenum() == 74){
  c = 74
  ej.setEvidence(ej.indexOf("D5"))
 }
 if(notelist[i].notenum() == 75){
  c = 75
  ej.setEvidence(ej.indexOf("D5#"))
 }
 if(notelist[i].notenum() == 76){
  c = 76
  ej.setEvidence(ej.indexOf("E5"))
 }
 if(notelist[i].notenum() == 77){
  c = 77
  ej.setEvidence(ej.indexOf("F5"))
 }
 if(notelist[i].notenum() == 78){
  c = 78
  ej.setEvidence(ej.indexOf("F5#"))
 }
 if(notelist[i].notenum() == 79){
  c = 79
  ej.setEvidence(ej.indexOf("G5"))
 }
 if(notelist[i].notenum() == 80){
  c = 80
  ej.setEvidence(ej.indexOf("G5#"))
 }
 if(notelist[i].notenum() == 81){
  c = 81
  ej.setEvidence(ej.indexOf("A5"))
 }
}


 def ei = mr.getMusicElement("s", i)
 if(notelist[i-1].notenum() == 60){
  c = 60
  ei.setEvidence(ei.indexOf("C4"))
 }
 if(notelist[i-1].notenum() == 61){
  c = 61
  ei.setEvidence(ei.indexOf("C4#"))
 }
 if(notelist[i-1].notenum() == 62){
  c = 62
  ei.setEvidence(ei.indexOf("D4"))
 }
 if(notelist[i-1].notenum() == 63){
  c = 63
  ei.setEvidence(ei.indexOf("D4#"))
 }
 if(notelist[i-1].notenum() == 64){
  c = 64
  ei.setEvidence(ei.indexOf("E4"))
 }
 if(notelist[i-1].notenum() == 65){
  c = 65
  ei.setEvidence(ei.indexOf("F4"))
 }
 if(notelist[i-1].notenum() == 66){
  c = 66
  ei.setEvidence(ei.indexOf("F4#"))
 }
 if(notelist[i-1].notenum() == 67){
  c = 67
  ei.setEvidence(ei.indexOf("G4"))
 }
 if(notelist[i-1].notenum() == 68){
  c = 68
  ei.setEvidence(ei.indexOf("G4#"))
 }
 if(notelist[i-1].notenum() == 69){
  c = 69
  ei.setEvidence(ei.indexOf("A4"))
 }
 if(notelist[i-1].notenum() == 70){
  c = 70
  ei.setEvidence(ei.indexOf("A4#"))
 }
 if(notelist[i-1].notenum() == 71){
  c = 71
  ei.setEvidence(ei.indexOf("B4"))
 }
 if(notelist[i-1].notenum() == 72){
  c = 72
  ei.setEvidence(ei.indexOf("C5"))
 }
 if(notelist[i-1].notenum() == 73){
  c = 73
  ei.setEvidence(ei.indexOf("C5#"))
 }
 if(notelist[i-1].notenum() == 74){
  c = 74
  ei.setEvidence(ei.indexOf("D5"))
 }
 if(notelist[i-1].notenum() == 75){
  c = 75
  ei.setEvidence(ei.indexOf("D5#"))
 }
 if(notelist[i-1].notenum() == 76){
  c = 76
  ei.setEvidence(ei.indexOf("E5"))
 }
 if(notelist[i-1].notenum() == 77){
  c = 77
  ei.setEvidence(ei.indexOf("F5"))
 }
 if(notelist[i-1].notenum() == 78){
  c = 78
  ei.setEvidence(ei.indexOf("F5#"))
 }
 if(notelist[i-1].notenum() == 79){
  c = 79
  ei.setEvidence(ei.indexOf("G5"))
 }
 if(notelist[i-1].notenum() == 80){
  c = 80
  ei.setEvidence(ei.indexOf("G5#"))
 }
 if(notelist[i-1].notenum() == 81){
  c = 81
  ei.setEvidence(ei.indexOf("A5"))
 }
 def si = mr.getMusicElement("s", i)
 def ssi = si.getHighestProbIndex()
 def sssi = si.getProb(ssi)
 println(sssi)
 println("入力されたソプラノ" + (i) + ":" +  values1[ssi])
 m = notelist[i-1].offset(480)
 n = notelist[i-1].onset(480)
   part1.addNoteElement(n, m, c, 100, 100)

 def ai = mr.getMusicElement("a", i)
 def aai = ai.getHighestProbIndex()
 def aaai = ai.getProb(aai)
  ai.setEvidence(aai)
 println(aaai + "アルト:　" + values2[aai])
 if(values2[aai] == "G3"){
   c1 = 55
  }
 if(values2[aai] == "G3#"){
   c1 = 56
  }
 if(values2[aai] == "A3"){
   c1 = 57
  }
 if(values2[aai] == "A3#"){
   c1 = 58
  }
 if(values2[aai] == "B3"){
   c1 = 59
  }
 if(values2[aai] == "C4"){
   c1 = 60
  }
 if(values2[aai] == "C4#"){
   c1 = 61
  }
 if(values2[aai] == "D4"){
   c1 = 62
  }
 if(values2[aai] == "D4#"){
   c1 = 63
  }
 if(values2[aai] == "E4"){
   c1 = 64
  }
 if(values2[aai] == "F4"){
   c1 = 65
  }
 if(values2[aai] == "F4#"){
   c1 = 66
  }
 if(values2[aai] == "G4"){
   c1 = 67
  }
 if(values2[aai] == "G4#"){
   c1 = 68
  }
 if(values2[aai] == "A4"){
   c1 = 69
  }
 if(values2[aai] == "A4#"){
   c1 = 70
  }
 if(values2[aai] == "B4"){
   c1 = 71
  }
 if(values2[aai] == "C5"){
   c1 = 72
  }
 if(values2[aai] == "C5#"){
   c1 = 73
  }
 if(values2[aai] == "D5"){
   c1 = 74
  }
 if(values2[aai] == "D5#"){
   c1 = 75
  }
 if(values2[aai] == "E5"){
   c1 = 76
  }
  part2.addNoteElement(n, m, c1, 100, 100)

 def ti = mr.getMusicElement("t", i)
 def tti = ti.getHighestProbIndex()
 def ttti = ti.getProb(tti)
 ti.setEvidence(tti)
 println(ttti + "テノール:" + values3[tti])
  if(values3[tti] == "C3"){
   c2 = 48
  }
  if(values3[tti] == "C3#"){
   c2 = 49
  }
  if(values3[tti] == "D3"){
   c2 = 50
  }
  if(values3[tti] == "D3#"){
   c2 = 51
  }
  if(values3[tti] == "E3"){
   c2 = 52
  }
  if(values3[tti] == "F3"){
   c2 = 53
  }
   if(values3[tti] == "F3#"){
   c2 = 54
  }
  if(values3[tti] == "G3"){
   c2 = 55
  }
  if(values3[tti] == "G3#"){
   c2 = 56
  }
  if(values3[tti] == "A3"){
   c2 = 57
  }
  if(values3[tti] == "A3#"){
   c2 = 58
  }
  if(values3[tti] == "B3"){
   c2 = 59
  }
  if(values3[tti] == "C4"){
   c2 = 60
  }
  if(values3[tti] == "C4#"){
   c2 = 61
  }
  if(values3[tti] == "D4"){
   c2 = 62
  }
  if(values3[tti] == "D4#"){
   c2 = 63
  }
  if(values3[tti] == "E4"){
   c2 = 64
  }
  if(values3[tti] == "F4"){
   c2 = 65
  }
  if(values3[tti] == "F4#"){
   c2 = 66
  }
  if(values3[tti] == "G4"){
   c2 = 67
  }
  if(values3[tti] == "G4#"){
   c2 = 68
  }
  if(values3[tti] == "A4"){
   c2 = 69
  }
  part3.addNoteElement(n, m, c2, 100, 100)

 def bi = mr.getMusicElement("b", i)
 def bbi = bi.getHighestProbIndex()
 def bbbi = bi.getProb(bbi)
 bi.setEvidence(bbi)
 println(bbbi + "バス:　　" + values4[bbi])
  if(values4[bbi] == "F2"){
   c3 = 41
  }
  if(values4[bbi] == "F2#"){
   c3 = 42
  }
  if(values4[bbi] == "G2"){
   c3 = 43
  }
  if(values4[bbi] == "G2#"){
   c3 = 44
  }
  if(values4[bbi] == "A2"){
   c3 = 45
  }
  if(values4[bbi] == "A2#"){
   c3 = 46
  }
  if(values4[bbi] == "B2"){
   c3 = 47
  }
  if(values4[bbi] == "C3"){
   c3 = 48
  }
  if(values4[bbi] == "C3#"){
   c3 = 49
  }
  if(values4[bbi] == "D3"){
   c3 = 50
  }
  if(values4[bbi] == "D3#"){
   c3 = 51
  }
  if(values4[bbi] == "E3"){
   c3 = 52
  }
  if(values4[bbi] == "F3"){
   c3 = 53
  }
  if(values4[bbi] == "F3#"){
   c3 = 54
  }
  if(values4[bbi] == "G3"){
   c3 = 55
  }
  if(values4[bbi] == "G3#"){
   c3 = 56
  }
  if(values4[bbi] == "A3"){
   c3 = 57
  }
  if(values4[bbi] == "A3#"){
   c3 = 58
  }
  if(values4[bbi] == "B3"){
   c3 = 59
  }
  if(values4[bbi] == "C4"){
   c3 = 60
  }
  if(values4[bbi] == "C4#"){
   c3 = 61
  }
  if(values4[bbi] == "D4"){
   c3 = 62
  }
 part4.addNoteElement(n, m, c3, 100, 100)

}
fileobj = new File(args[0])
scc2.toWrapper().toMIDIXML().writefileAsSMF("results/" + fileobj.getName() + ".mid")
//SCCXMLWrapper scc3 = scc2.toWrapper();
//scc3.writefile("shise.xml");

