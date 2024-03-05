@echo off

java -Djava.security.policy=rmi.policy DicionarioClient escrever circulo nao
java -Djava.security.policy=rmi.policy DicionarioClient escrever quadrado sim
java -Djava.security.policy=rmi.policy DicionarioClient escrever retangulo sim
java -Djava.security.policy=rmi.policy DicionarioClient escrever linha nao
java -Djava.security.policy=rmi.policy DicionarioClient ler

pause