# Di2win_Desafio_Backend
 Desafio de programação JAVA Backend
Habilidades requeridas: Java, API REST, SQL
Descrição do cenário
A Di2win recebeu uma demanda de implementar a primeira versão do sistema de Conta online de um cliente. Esse sistema deverá fornecer uma API REST com endpoints que serão consumidos por outros sistemas da empresa, porém a demanda atual envolve unicamente o backend desse sistema.
O sistema deverá gerenciar os clientes e suas contas digitais, armazenar as informações em banco de dados relacional e expor serviços REST para acesso.
Necessidades:
Deve ser possível criar e remover clientes
• Um cliente será identificador por seu: Nome, CPF e data de Nascimento
• O CPF deverá ser válido (validar utilizando o dígito verificador) e deverás ser único na base de dados, impedindo dois clientes com o mesmo número de CPF.
A conta digital Di2win deve prover as funcionalidades:
• A conta deve ser criada utilizando o CPF do cliente;
• Cada conta deverá ter saldo, número e agência disponíveis para consulta
• Necessário ter funcionalidade de emissão de extrato de transações de conta de um determinado período
• Um cliente poderá bloquear a conta a qualquer momento
• As seguintes operações devem ser possíveis: Saque e depósito
o Depósito: Liberado para todas as contas ativas e desbloqueadas
o Saque é permitido para todas as contas ativas e desbloqueadas desde que exista saldo suficiente para a transação e o valor não supere um limite diário configurável: ex: R$2000,00
• A conta nunca pode ter saldo negativo
• A conta poderá ser bloqueada ou desbloqueada a qualquer momento.
Escopo mínimo desejado:
• Criação de Serviços para:
o Criar uma conta
o Efetuar depósito em uma conta
o Consultar saldo de conta
o Realizar saque de conta
o Bloquear conta
o Recuperar extrato de conta
• Diferenciais;
o Extrato por período
o Documentação
o Testes
• Iremos avaliar:
o Código fonte
o Script de banco de dados
o Organização
o Boas práticas
o Diferenciais
Orientações
• Utilizar a linguagem de programação JAVA
• Desenvolver utilizando as melhores práticas que achar necessário.
• Banco de dados: Qualquer banco de dados Relacional
• A modelagem de dados do banco de dados fica a seu critério
• Iremos considerar qualquer implementação, trecho de código, intenção e documentação compartilhada.
• Disponibilizar seu código fonte em um GitHub e compartilhar o endereço conosco.
Diferenciais:
• Utilização de melhores práticas, padrões e micros serviços são considerados diferenciais.
• Você tem autonomia para definir arquitetura da solução, design de dados, paradigmas e documentação
• Ao fim do processo, enviar e-mail com cópia para a pessoa com quem vem mantendo contato.
• O tempo total utilizado para a responder o desafio e o atendimento aos itens essenciais e diferenciais serão levados em consideração.
