import jp.crestmuse.cmx.inference.*
//import jp.crestmuse.cmx.inference.MusicRepresentation.MusicElement
import jp.crestmuse.cmx.filewrappers.*


scc = CMXFileWrapper.readfile(args[0])
partlist = scc.getPartList()
division = scc.getDivision()
chordlist = scc.getChordList()
def a = division / 2

 notelist = partlist[0].getNoteOnlyList()
 onset = notelist[0].onset()
 notenum = notelist[0].notenum()
 chordname = chordlist[0].content()

def barline = scc.getBarlineList()
def j = 0
int i = 0

 l = notelist.length
def d = notelist[l-1].offset / a
println("int[] s = [")print("0,")
for(i=0;i<=d;i++){
    n=a*i
    if(j < l && notelist[j].onset() == n){
      print(notelist[j].notenum())
      print(",")
      j++
    }
    else if(j-1>=0){
       print(notelist[j-1].notenum())
       print(",")
    }
}   

println("0,]")



notelist = partlist[1].getNoteOnlyList()



 l = notelist.length
 d = notelist[l-1].offset / a
  println("int[] a = [")print("0,")
 j = 0
for(i=0;i<=d;i++){
    n=a*i
    if(j < l && notelist[j].onset() == n){
      print(notelist[j].notenum())
      print(",")
      j++
    }
    else if(j-1>=0){
       print(notelist[j-1].notenum())
       print(",")
    }
}   

println("0,]")


notelist = partlist[2].getNoteOnlyList()



 l = notelist.length
 d = notelist[l-1].offset / a
  println("int[] t = [")print("0,")
 j = 0
for(i=0;i<=d;i++){
    n=a*i
    if(j < l && notelist[j].onset() == n){
      print(notelist[j].notenum())
      print(",")
      j++
    }
    else if(j-1>=0){
       print(notelist[j-1].notenum())
       print(",")
    }
}   

println("0,]")



notelist = partlist[3].getNoteOnlyList()


 l = notelist.length
 d = notelist[l-1].offset / a
  println("int[] b = [")print("0,")
 j = 0
for(i=0;i<=d;i++){
    n=a*i
    if(j < l && notelist[j].onset() == n){
      print(notelist[j].notenum())
      print(",")
      j++
    }
    else if(j-1>=0){
       print(notelist[j-1].notenum())
       print(",")
    }
}   

println("0,]")

println("""

def S = []
def A = []
def T = []
def B = [] 
l = s.length

for(i=0;i<l-1;i++){
	if(s[i] == s[i+1] && a[i] == a[i+1] && t[i] == t[i+1] && b[i] == b[i+1]){}
	else if(60<=s[i] && 81>=s[i] && 55<=a[i] && 76>=a[i] && 48<=t[i] && 69>=t[i] && 41<=b[i] && 62>=b[i]
 && a[i+1]==0 && t[i+1]==0 && b[i+1]==0 && s[i+1]==0){
		S.add(s[i])
		A.add(a[i])
		T.add(t[i])
		B.add(b[i])
	}
	else if(60<=s[i+1] && 81>=s[i+1] && 55<=a[i+1] && 76>=a[i+1] && 48<=t[i+1] && 69>=t[i+1] && 41<=b[i+1] && 62>=b[i+1] && a[i]==0 && t[i]==0 && b[i]==0 && s[i]==0){
		S.add(s[i])
		A.add(a[i])
		T.add(t[i])
		B.add(b[i])
	}
	else if(60<=s[i+1] && 81>=s[i+1] && 55<=a[i+1] && 76>=a[i+1] && 48<=t[i+1] && 69>=t[i+1] && 41<=b[i+1] && 62>=b[i+1] && 60<=s[i] && 81>=s[i] && 55<=a[i] && 76>=a[i] && 48<=t[i] && 69>=t[i] && 41<=b[i] && 62>=b[i]){
		S.add(s[i])
		A.add(a[i])
		T.add(t[i])
		B.add(b[i])
	}
}
S.add(0)
A.add(0)
T.add(0)
B.add(0)
for(i=1;i<S.size();i++){

  print(S[i] +",")
  print(A[i-1] +",")print(A[i] +",")
  print(T[i-1] +",")print(T[i] +",")
  print(B[i-1] +",")println(B[i] +",")

}
 """)


/*notelist[long-1].offset()最後のオフセットが何個目か。
n=8分音符の拍数*/


	
