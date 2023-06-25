SIMULADOR DE FORÇA BRUTA SOBRE UMA APLICAÇÃO INSEGURA QUE UTILIZA DES

O teste simula um ataque de força bruta em uma aplicação insegura
A aplicação só contem a comparação de hashes com algoritmo de criptografia DES, e não possui nenhum outro tipo de requisito de segurança
Será feito o cadastro de um usuário com três tipos de senha
CADASTRO:

http://localhost:8989/v1/redefinePassword/?user=joao.silva&password=abA1

VALIDAÇÃO DE LOGIN:

http://localhost:8989/v1/validUserPassword/?user=joao.silva&password=abA1

O ataque de força bruta foi programado da seguinte forma:

- Testa 3 contextos de senha com base em um tamanho definido como parametro da request da API http://localhost:8080/v1/breakpassword/?user=joao.silva&length=4. 
No exemplo acima, todas as senhas geradas pelo gerador de texto terão 4 caracteres.

- O contextoA possui somente letras minusculas
- O contextoB faz o teste com letras maiusculas ou minusculas ou numeros podendo ser a0B1, 14s0 B5ty e entre outros
- O contextoC faz o teste com letras maiusculas ou maiusculas, ou numeros ou caracteres especiais

O quebrador de senha testa os três contextos utilizando programação paralela, nesse caso o processador irá gerenciar 300 threads, e a thread que trouzer o resultado verdadeiro interromperá todo os fluxo.

O resultado do teste é salvo em um arquivo de log