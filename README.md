# Mini EP 8

O problema é calcular os valores devidos em INSS e IRRF dada uma base de cálculo (salário bruto menos possíveis descontos).
Utilizei o site [iDinheiro](https://www.idinheiro.com.br/calculadoras/calculadora-de-salario-liquido/) para gerar os casos de teste.

O processo consistiu em:
1. Gerar os casos de teste a partir do site.
2. Criar o arquivo de teste e a interface da calculadora.
3. Criar uma primeira versão da calculadora que passava nos testes (macarronada de código).
4. Reorganizar o código repetidamente. Acabei por criar uma estrutura que contém as informações básicas das tabelas de INSS e IRRF, como limites superior/inferior, alíquota e base de cálculo, isso facilita bastante para tratar futuras atualizações dessa tabela (que sabe lá quando vai acontecer, rs). Também fiz alguns ajustes para lidar com as questões de trabalhar com divisões de valores monetários (que tratei como inteiros, i.e. quantidade de centavos).

Dado o problema e bons exemplos (i.e. que bem representam o problema), a aplicação de TDD é trivial, para cada entrada, temos
saídas esperadas e realizamos comparações como asserção do comportamento. A parte mais crítica nesse contexto, acaba não sendo
TDD em si, porque a aplicação é muito simples, mas na definição do problema e do que esperamos que o código faça, verificar
os valores de uma calculadora é um processo simples, comparamos valores e está tudo certo, mas normalmente começam a entrar
requisitos no meio do caminho, tipo um pedido de "quero que a calculadora avise quando acontecer X, Y e Z", e daí temos
que incluir testes de instrumentação, pensar nas interfaces pra suprir isso e etc. Mas, em linhas gerais, acho positiva a
metologia para evitar acoplamento de testes ao código e evitar fazer quaisquer modificações nos testes, isso ajuda bastante
a não cair no ["testing that the code that we wrote is the code that we wrote"](https://youtu.be/Bq_oz7nCNUA).