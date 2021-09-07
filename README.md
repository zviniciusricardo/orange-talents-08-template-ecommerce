# Mercado Livre 
<b> Orange Talents, turma 8 </b>


## Funcionalidade: Não podemos ter dois usuários com o mesmo email

### Cenário:
Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

### O que seria bom ver nessa resposta?
Peso 10: Reutilização da validação genérica implementada no projeto da Casa do Código

### O que penaliza sua resposta?

* Penalidade - 5: Criação de setters e getters na classe. Não é necessário dada a especificação.
* Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
* Penalidade - 5: Criação de uma classe específica de conversão entre os dados que vieram da requisição e o objeto do tipo específico, comumente conhecida como Converter. Não é necessária porque pode ser feito direto no método do controller ou através de um método na classe que representa o DTO.
* Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
* Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

### Resposta do Especialista:

Pego o código da validação genérica que implementei na Casa do Código e colo aqui no projeto.
Reutilizo a annotation de validação única

________
## Cadastro de nova Categoria

<b>Necessidades</b>

No mercado livre você pode criar hierarquias de categorias livres. 
Ex: Tecnologia -> Celulares -> Smartphones -> Android, iOS etc. 
Perceba que o sistema precisa ser flexível o suficiente para que essas sequências sejam criadas.
Toda categoria tem um nome
A categoria pode ter uma categoria mãe

<b>Restrições</b>

* O nome da categoria é obrigatório
* O nome da categoria precisa ser único
* Resultado esperado
* Categoria criada e status 200 retornado pelo endpoint.
* Caso exista erros de validação, o endpoint deve retornar 400 e o json dos erros.

### Categoria

Tabela Categoria
id (pk)
name
category_id (fk)

Tabela Produtos
id (pk)
name
category_id (fk)

Eu faria uma tabela de categorias com auto-relacionamento 
permitindo n subcategorias, o relacionamento com produtos 
seria algo do tipo: 
produto--<produto_categoria>-----categoria

tentando resumir ..... 
(1) categoria refrigerante, sub colas , sub sem açucar 
(2) categoria dieteticos , sub refrigerantes , o produto motta-cola 
ligado as categorias (1) e (2) – 


## Funcionalidade: Cadastro de categorias



### Cenário:

<b>O que seria bom ver nessa resposta?</b>

* Peso 2: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber 
um POST, configurado para validar entrada de dados e também para receber os dados como JSON.
* Peso 1: Utilização de uma classe específica para receber os dados do novo cadastro. O famoso DTO.
* Peso 1: Utilização das annotations de validação da Bean Validation na classe do DTO apenas no atributo nome.
* Peso 1: Criação do método/classe de conversão de conversão dos dados da nova categoria para um objeto do tipo Categoria.
* Peso 1: Criação da classe que representa a Categoria com as informações
* Peso 1: Utilização das annotations de validação da Bean Validation na classe de domínio apenas no atributo nome.
* Peso 1: Utilização do construtor na classe Categoria recebendo apenas o nome.
 
* Peso 1: Criação do setter para receber a referência para a categoria mãe.

* Peso 1: Utilização do EntityManager ou Repository específico para a inserção da Categoria

<b>O que penaliza sua resposta?</b>

* Penalidade - 1: Criação de mais de um setter. Não é necessário dada a especificação.
* Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
* Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
* Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

<b>Resposta do Especialista:</b>

Crio um controller com um método para receber o post com os dados da nova categoria.
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados da nova categoria. Ali também eu uso a anotação @Valid e @RequestBody para indicar a necessidade de validação e que o dado vem no corpo da requisição.
Na classe que tem os dados de entrada, deixo como dado obrigatório apenas o nome da categoria. Já que ela pode, ou não, ter uma categoria mãe.
Ainda na classe que representa os dados de uma nova categoria, eu utilizo atributos do tipo Integer/Long para representar o id da possível categoria mãe. Simplesmente porque é o identificador necessário para que possamos buscar os objetos relativos através do nosso ORM.
Dentro do método do controller eu converto os dados do DTO para a criação de um objeto do tipo Categoria. Faço isso através de um método adicionado no DTO que retorna uma Categoria em função dos valores dos atributos do DTO. Aqui tem um detalhe especial. Para eu conseguir construir um novo objeto do tipo Categoria eu talvez precise construir um outro objeto do tipo Categoria representando a categoria mãe. Preciso sair do id da categoria mãe para uma Categoria de fato. Para fazer isso eu peço um EntityManager no método de conversão, para conseguir acessar o método findById. Se quisesse usar o princípio da segregação pela interface, eu poderia receber como argumento no método de conversão do DTO uma parâmetro do tipo Function e usar um method reference para passar a referência para o método find do EntityManager.
Ainda no método de conversão, é importante fazer um if para verificar se tem categoria mãe.
Crio a classe Categoria com os atributos devidamente anotados com a Bean Validation. Um detalhe é que no construtor eu vou receber apenas o nome da categoria, já que a mãe, nesse caso, não é obrigatória.
Crio um setter para a categoria mãe na classe Categoria.
Faço o mapeamento na classe Categoria para que o Hibernate consiga persistir os objetos. Importante aqui a utilização @ManyToOne em cima do atributo do tipo Categoria que representa a mãe.
Recebo injetado o EntityManager no controller e gravo a categoria no banco de dados.
Deixo o Hibernate criar a tabela para mim no banco de dados. Poderia ter criado também o script e executado contra o banco