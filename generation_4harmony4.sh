#!/bin/sh

for filename in `cat $1`
#for filename in ../ソプラノ課題/kadai*.mid
do
basename=`basename $filename .xml`
#c_name=nonchordmodel_ver2_"${basename}"_04down
echo 　start generation $filename
groovy main_program.groovy scc/$filename $2
#java jp.crestmuse.cmx.commands.SCC2MIDI -smf results/$c_name.mid shise.xml
#cmx scc2midi -smf results/$c_name.mid shise.xml
echo finish $filename
done

