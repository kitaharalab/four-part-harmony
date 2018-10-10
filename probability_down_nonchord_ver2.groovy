import jp.crestmuse.cmx.math.*
import static jp.crestmuse.cmx.math.Utils.*

DoubleMatrix.mixin(Operations)

def parser = new XmlParser()
def bif = parser.parse(args[0])
//def bif = parser.parse("sanbi_ver4_nonchordmodel_ver2.xml")

def v1 = parseMatrix(bif.NETWORK.DEFINITION[2].TABLE[0].text())
def tate=0                       /*配列の縦の要素数*/
def s_now=4                      /*現在のソプラノ*/
def a_now=0                      /*配列の横の要素数 現在のアルト*/
def s_a = 0                      /*現在のソプラノとアルトの高低差*/
def a_before=0                  /*一つ前のアルト*/
def aa=0                         /*一つ前と現在のアルトの高低差*/
def s_length=23                  /*ソプラノの要素数*/
def a_length=23                  /*アルトの要素数*/
def b_length=23                  /*バスの要素数*/
int i=0
def q=0.4
//def q=0
//def name="nonchordmodel_ver2_00down.xml"
def name=args[1]

for(yoko=0;yoko<=(b_length)-1;yoko++){
	for(tate=0;tate<(b_length)*(b_length);tate++){ 
		value = v1.get(tate+i,yoko)
		value = value*q        
        	v1.set(tate+i, yoko, value)
	}
	i=i+(23*23)
}
bif.NETWORK.DEFINITION[2].TABLE[0].setValue(toString2(v1))

/*******************************************************************/
def v2 = parseMatrix(bif.NETWORK.DEFINITION[3].TABLE[0].text())
    a_now=6                     /*現在のソプラノ*/
    a_t=0                        /*現在のアルトとテノールの高低差*/
    value=1                      /*配列に代入する値*/
def t_now=0                      /*配列の横の要素数 現在のテノール*/
def t_before=0                   /*一つ前のテノール*/
def tt=0                         /*一つ前と現在のテノールの高低差*/
def t_length=23                  /*テノールの要素数*/
    s_now=11
i=0


for(yoko=0;yoko<=(b_length)-1;yoko++){
	for(tate=0;tate<(b_length)*(b_length);tate++){ 
		value = v2.get(tate+i,yoko)
		value = value*q        
        	v2.set(tate+i, yoko, value)
	}
	i=i+(23*23)
}
bif.NETWORK.DEFINITION[3].TABLE[0].setValue(toString2(v2))

/*******************************************************************/

def v3 = parseMatrix(bif.NETWORK.DEFINITION[5].TABLE[0].text())
    s_now=18                      /*現在のソプラノ*/
def b_now=0                      /*配列の横の要素数 現在のバス*/
def t_b=0                        /*現在のテーノルとバスの高低差*/
def b_before=0                   /*一つ前のバス*/
def bb=0                         /*一つ前と現在のバスの高低差*/
    s_now=18

i=0
for(yoko=0;yoko<=(b_length)-1;yoko++){
  for(tate=0;tate<(b_length);tate++){
    value = v3.get(tate+i, yoko)
    value = value*q
    v3.set(tate+i, yoko, value)
  }
  i = i + 23
}
  //for(tate=0;tate<=(b_length)-1;tate++){
  //	for(yoko=0;yoko<(b_length);yoko++){
  //        value = v3.get(tate, yoko)
  //        value = value*q
  //        }
  //        v3.set(tate, b_now, value)
  //  }
 
bif.NETWORK.DEFINITION[5].TABLE[0].setValue(toString2(v3))

/*******************************************************************/


def v5 = parseMatrix(bif.NETWORK.DEFINITION[7].TABLE[0].text())


tate=0                       /*配列の縦の要素数*/
a_now=0                      /*配列の横の要素数 現在のアルト*/
a_before=0                  /*一つ前のアルト*/
aa=0                         /*一つ前と現在のアルトの高低差*/
a_length=23                  /*アルトの要素数*/
c_length=29
s_now=4
i=0

i=0


for(yoko=0;yoko<=(b_length)-1;yoko++){
	for(tate=0;tate<(b_length)*(b_length);tate++){ 
		value = v5.get(tate+i,yoko)
		value = value*q        
        	v5.set(tate+i, yoko, value)
	}
	i=i+(23*23)
}
bif.NETWORK.DEFINITION[7].TABLE[0].setValue(toString2(v5))

/*******************************************************************/

def v6 = parseMatrix(bif.NETWORK.DEFINITION[8].TABLE[0].text())
value=1                      /*配列に代入する値*/
t_now=0                      /*配列の横の要素数 現在のテノール*/
t_before=0                   /*一つ前のテノール*/
tt=0                         /*一つ前と現在のテノールの高低差*/
t_length=23                  /*テノールの要素数*/
s_now=11
a_now=6
i=0


for(yoko=0;yoko<=(t_length)-1;yoko++){
	for(tate=0;tate<(b_length)*(b_length);tate++){ 
		value = v6.get(tate+i,yoko)
		value = value*q        
        	v6.set(tate+i, yoko, value)
	}
	i=i+(23*23)
}
bif.NETWORK.DEFINITION[8].TABLE[0].setValue(toString2(v6))

/*******************************************************************/

def v7 = parseMatrix(bif.NETWORK.DEFINITION[9].TABLE[0].text())
b_now=0                      /*配列の横の要素数 現在のバス*/
b_before=0                   /*一つ前のバス*/
bb=0                         /*一つ前と現在のバスの高低差*/
b_length=23                  /*バスの要素数*/
    s_now=18

i=0
for(yoko=0;yoko<=(b_length)-1;yoko++){
	
	for(tate=0;tate<(b_length);tate++){
        value = v7.get(tate+i, yoko)
        value = value*q
	v7.set(tate+i, yoko, value)
        }
        i=i+23
  }
bif.NETWORK.DEFINITION[9].TABLE[0].setValue(toString2(v7))

/*******************************************************************/


def np = new XmlNodePrinter(new PrintWriter(name))
np.setPreserveWhitespace(true)
np.print(bif)

