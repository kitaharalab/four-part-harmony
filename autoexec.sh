#!/bin/bash

nohup ./generation_4harmony4.sh test_list_0a.txt nonchordmodel_20170324_04down.xml >& log_0a &
nohup ./generation_4harmony4.sh test_list_0b.txt nonchordmodel_20170324_04down.xml >& log_0b &
nohup ./generation_4harmony4.sh test_list_0c.txt nonchordmodel_20170324_04down.xml >& log_0c &
nohup ./generation_4harmony4.sh test_list_1.txt nonchordmodel_20170324_04down.xml >& log_1 &

