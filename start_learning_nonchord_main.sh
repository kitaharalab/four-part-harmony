Dataname="cpt_nonchordmodel_main2.arff"

echo "@relation mididateWEKA" > $Dataname
echo "@attribute s {60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,0}" >> $Dataname
echo "@attribute s1 {60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,0}" >> $Dataname
echo "@attribute s2 {60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,0}" >> $Dataname
echo "@attribute a {55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,0}" >> $Dataname
echo "@attribute a1 {55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,0}" >> $Dataname
echo "@attribute a2 {55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,0}" >> $Dataname
echo "@attribute t {48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,0}" >> $Dataname
echo "@attribute t1 {48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,0}" >> $Dataname
echo "@attribute t2 {48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,0}" >> $Dataname
echo "@attribute b {41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,0}" >> $Dataname
echo "@attribute b1 {41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,0}" >> $Dataname
echo "@attribute b2 {41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,0}" >> $Dataname
echo "" >> $Dataname
echo "@data" >> $Dataname

for filename in `cat train_list.txt`
#for filename in scc/*.xml
#for filename in trans_wise_finish_scc_new/*.xml
do
groovy learning1_nonchordmodel.groovy scc/$filename > learning2.groovy
#groovy learning1_2013newmodelver.groovy $filename > learning2.groovy
groovy learning2.groovy >> $Dataname
echo $filename
done

