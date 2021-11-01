# Mercado Livre REST API

- [x] 0-1-1-cadastro-usuario
- [x] 1-1-1-nao-pode-ter-usuario-mesmo-email
- [x] 1-1-2-cadastro-categoria
- [x] 1-2-2-trabalhando-com-usuario-logado
- [x] 2-2-2-usuario-logado-cadastra-produto
- [x] 2-2-3-adiciona-imagem-produto
- [x] 2-3-3-adiciona-opiniao-sobre-produto
- [x] 3-3-3-faca-uma-pergunta
- [x] 3-3-4-codigo-para-pagina-detalhe
- [x] 3-4-4-finaliza-compra-parte-1
- [ ] 4-4-4-finaliza-compra-parte-2

__________________________________________________________


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


### Funcionalidade: Cadastro de categorias

#### Cenário:

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



## Implementação sistema de login e autenticação via token Jwt

Primeiramente eu adicionaria as dependências do spring security.
Após, eu criaria uma classe de configuração anotada com @Configuration e @EnableWebSecurity.
		
		obs: O padrão do Spring Security é bloquear todos os endpoints
		quais endereços eu quero liberar e qual eu quero proteger
		sobrescrever métodos da WebSecurityConfigurerAdapter

Após, nos criamos a parte de identificação de roles nos usuários implementando a interface
UserDetails e seus métodos. Cada método dá informações ao Spring Security do que esperar do 
cliente na hora da autenticação. Junto com a implementação da interface, a criação da entidade
Perfil (do usuário) que implementa outra interface GrantedAuthorit, com o nome de cada role que será atribuída
à entidade autenticada.

Após isso, eu teria que restringir acesso à funcionalidades sensíveis da aplicação como alterar e/ou excluir
recursos. Isso é feito pelo método sobrescrito da WebSecurityConfigurerAdapter, configure(HttpSecurity http).

	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

Com a linha de código acima, nossa aplicação agora não guarda estado com sessions e deixa para que o cliente
implemente a parte de formulários. Nossa aplicação agora somente irá gerar um Token e deixar o gerencia
mento do mesmo para o cliente que deverá o apresentar a cada nova requisição.

Vamos instalar as dependências do Jwt;

Criamos um service para autenticação do usuário logado (com as roles).
Essa classe precisa ser anotada como um bean @Service.
Ela implementa a interface UserDetailService e um método loadUserByUserName() e recebe o email.
No caso, a coferência de validade da senha é feita por baixo dos panos com o Jwt.

É preciso agora, com o Jwt configurado, criar um TokenService onde vou definir parâmetros e comportamentos
que irão contidos no token, como data de expiração e tipo de encriptação. Uso o Jwts.builder para contruir o token.

Crio também uma entidade TokenDto para representar o formulário preenchido com dados do login (email e senha)

E, por fim, um controller com um endpoint onde o usuário deverá se autenticar.
Precisamos capturar esses dados da request do cliente e convertê-la num UsernamePasswordAuthenticationToken,
que é outra classe implementada pela Jwt e serve para fazer as validações que pré-definimos antes como
data de expiração e encode.

Após o usuário se autenticar, geramos um token e enviamos como resposta no body. Também explicitamos
o tipo de autenticação. No caso 'Bearer'.

Caso os dados sejam inválidos ou haja um erro no meio do caminho, eu respondo com um badRequest 404.


## Cadastro Produtos

Eu crio o ProdutoController que recebe um tipo Form nos parâmetros do método.
Esse ProdutoForm conterá as informações obrigatórias e as validações para a construção do nosso Produto.
Crio um método de conversão de Entidade em Dto (classe Response)
Crio uma subclasse para representar as características do produto.
No construtor do produto, preciso indicar uma Lista de ao menos 3 características para o produto.
Ao receber o produto, preciso validar os dados e usar o Spring Data para mapear a entidade e persistir
no banco.

Cada produto pode ter várias características e apenas uma categoria.
Sendo assim, mapeamos nossa classe Caracteristicas como @ManyToOne, e o Usuario tambem, já que 
um produto pode pertencer a diversos usuários.

Ao fazer a requisição, o cliente precisará se autenticar primeiro no path "/auth", pegar o token fornecido
e então ir para o contexto "/produtos", e enviar uma requisição com parâmetro de autenticação do tipo "Bearer"

Ele também precisa informar o token no cabeçalho junto com a requisição no corpo. 
A requisição é um json com as informações obrigatórias de ProdutoForm mais um array de características
não menor que 3.

## Funcionalidade: Cadastro de produto

Cenário:

Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

### O que seria bom ver nessa resposta?

Peso 0.5: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber um POST, configurado para validar entrada de dados e também para receber os dados como JSON.
Peso 0.5: Utilização de uma classe específica para receber os dados do novo cadastro do produto. O famoso DTO.
Peso 1.5: Utilização de uma classe específica para receber os dados das características do novo produto
Peso 1.5: Utilização de um atributo do tipo Set para a coleção de características
Peso 0.5: Criação do equals e hashcode na classe que representa os dados da nova característica
Peso 0.5: Utilização das annotations de validação da Bean Validation na classe do DTO do novo produto.
Peso 0.25: Atributos de tipo padrão da linguagem anotados
Peso 0.25: Coleção anotada com @Size
Peso 1: Criação do método/classe de conversão de conversão dos dados do novo produto para um objeto do tipo Produto
Peso 0.4: Para receber o usuário que é o dono do produto como argumento do método de conversão
Peso 0.2: Para receber o EntityManager como parâmetro/atributo do método/classe de conversão.
Peso 0.4: Por garantir que um produto seja criado com as suas características.
Peso 0.5: Criação da classe que representa o Produto com as informações
Peso 2: Utilização do construtor na classe Produto recebendo tudo que precisa.
Peso 0.8: Recebe a coleção de características do produto
Peso 0.6: Recebe a categoria do produto
Peso 0.6 Recebe as outras informações
Peso 0.5: Criação da classe que representa a Característica do produto
Peso 0.5: Utilização das annotations de validação da Bean Validation na classe de produto e característica.
Peso 0.5: Utilização do EntityManager ou Repository específico para a inserção do Produto.

O que penaliza sua resposta?

Penalidade - 5: Criação de setter. Não é necessário dada a especificação.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.



Resposta do Especialista:

Crio um controller com um método para receber o post com os dados da nova categoria.
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados do novo produto. Ali também eu uso a anotação @Valid e @RequestBody para indicar a necessidade de validação e que o dado vem no corpo da requisição.
Aqui é um cadastro um pouco mais complicado, dado que o novo produto, além de informações de tipos que já existem na linguagem, como nome, quantidade, e descrição, tem também as suas características. Então, na classe que representa os dados de entrada de um novo produto, eu adiciono um atributo do tipo Set<NovaCaracteristicaRequest>. Justamente para representar os dados das características que vão chegar. Eu utilizo o Set aqui porque não queremos características com nomes iguais para o mesmo produto.
Na classe NovaCaracteristicaRequest eu tenho os atributos que representam a chave e o valor da característica. Preciso criar um equals e hashcode na classe NovaCaracteristicaRequest para que ela possa ser usada em uma coleção do tipo Set. Uso o nome como atributo que define a igualdade entre características.
Além das características, para representar a associação com a categoria, adiciono o atributo do tipo Integer/Long para receber o id categoria do produto
Adiciono as annotations de validação em cima de cada atributo. Atenção especial para a utilização do @Size para controlar o mínimo de características. E como colocamos o atributo que representa a coleção de características como uma implementação da interface Set e implementamos o equals e hashcode na NovaCaracteristicaRequest, automaticamente protegemos de nomes duplicados.
Dentro do método do controller eu converto os dados do DTO para a criação de um objeto do tipo Produto. Faço isso através de um método adicionado no DTO que retorna um Produto em função dos valores dos atributos do DTO. Aqui tem um detalhe especial. Para eu conseguir construir um novo objeto do tipo Produto eu preciso construir um outro objeto do tipo Categoria representando a categoria do produto. Preciso sair do id da categoria mãe para uma Categoria de fato. Para fazer isso eu peço um EntityManager no método de conversão, para conseguir acessar o método findById. Se quisesse usar o princípio da segregação pela interface, eu poderia receber como argumento no método de conversão do DTO uma parâmetro do tipo Function e usar um method reference para passar a referência para o método find do EntityManager. Além disso eu também preciso associar o novo produto com seu dono. Então eu também peço um parâmetro do tipo Usuario no método de conversão.
Crio a classe Produto com os atributos devidamente anotados com a Bean Validation. Um detalhe é que eu preciso receber a coleção de novas características no construtor. Eu vou decidir receber a coleção de NovaCaracteristicaRequest direto, acoplando essas camadas. O motivo dessa decisão é que decidi fazer com que a classe que CaracteristicaProduto tenha uma referência ao produto. E aí caí no dilema do ovo e da galinha… Como eu não tenho produto, como poderia criar a característica. Preciso de um jeito de receber os dados de cada característica e aí, a partir da criação do produto, criar a caractéristica em si. E não posso deixar de passar esses dados no construtor, já que o conjunto de características é obrigatório.
Para fechar o código mencionado acima, eu adiciono o método de conversão lá na classe NovaCaracteristicaRequest, para sair dos dados da característica para uma Característica de fato. Neste método eu recebo como argumento um produto :).
Faço o mapeamento na classe Produto para que o Hibernate consiga persistir os objetos. Importante aqui a utilização @OneToMany em cima do atributo do tipo Set<CaracteristicaProduto>. Aqui também é importante usar o parâmetro mappedBy no mapeamento da coleção, já que este mapeamento simplesmente é o mesmo do @ManyToOne que vai existir na classe CaracteristicaProduto.
Adiciono as annotations de validação da bean validation na classe Produto.
Crio a classe CaracteristicaProduto recebendo o nome, descricao e produto como argumento no construtor.
Faço o mapeamento para que o hibernate consiga persistir objetos do tipo CaracteristicaProduto. Importante lembrar do ManyToOne em cima do atributo do tipo Produto.
Adiciono as annotations de validação da bean validation na classe Produto.
Recebo injetado o EntityManager no controller  e gravo o produto no banco de dados.
Deixo o Hibernate criar a tabela para mim no banco de dados. Poderia ter criado também o script e executado contra o banco


### Formulário Resposta 

Eu crio o ProdutoController que recebe um tipo Form nos parâmetros do método.
Esse ProdutoForm conterá as informações obrigatórias e as validações para a construção do nosso Produto.
Crio um método de conversão de Entidade em Dto (classe Response)
Crio uma subclasse para representar as características do produto.
No construtor do produto, preciso indicar uma Lista de ao menos 3 características para o produto.
Ao receber o produto, preciso validar os dados e usar o Spring Data para mapear a entidade e persistir
no banco.

Cada produto pode ter várias características e apenas uma categoria.
Sendo assim, mapeamos nossa classe Caracteristicas como @ManyToOne, e o Usuario tambem, já que 
um produto pode pertencer a diversos usuários.

Ao fazer a requisição, o cliente precisará se autenticar primeiro no path "/auth", pegar o token fornecido
e então ir para o contexto "/produtos", e enviar uma requisição com parâmetro de autenticação do tipo "Bearer"

Ele também precisa informar o token no cabeçalho junto com a requisição no corpo. 
A requisição é um json com as informações obrigatórias de ProdutoForm mais um array de características
não menor que 3.

## Funcionalidade: Cadastro de produto

Cenário:

Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

### O que seria bom ver nessa resposta?

Peso 0.5: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber um POST, configurado para validar entrada de dados e também para receber os dados como JSON.
Peso 0.5: Utilização de uma classe específica para receber os dados do novo cadastro do produto. O famoso DTO.
Peso 1.5: Utilização de uma classe específica para receber os dados das características do novo produto
Peso 1.5: Utilização de um atributo do tipo Set para a coleção de características
Peso 0.5: Criação do equals e hashcode na classe que representa os dados da nova característica
Peso 0.5: Utilização das annotations de validação da Bean Validation na classe do DTO do novo produto.
Peso 0.25: Atributos de tipo padrão da linguagem anotados
Peso 0.25: Coleção anotada com @Size
Peso 1: Criação do método/classe de conversão de conversão dos dados do novo produto para um objeto do tipo Produto
Peso 0.4: Para receber o usuário que é o dono do produto como argumento do método de conversão
Peso 0.2: Para receber o EntityManager como parâmetro/atributo do método/classe de conversão.
Peso 0.4: Por garantir que um produto seja criado com as suas características.
Peso 0.5: Criação da classe que representa o Produto com as informações
Peso 2: Utilização do construtor na classe Produto recebendo tudo que precisa.
Peso 0.8: Recebe a coleção de características do produto
Peso 0.6: Recebe a categoria do produto
Peso 0.6 Recebe as outras informações
Peso 0.5: Criação da classe que representa a Característica do produto
Peso 0.5: Utilização das annotations de validação da Bean Validation na classe de produto e característica.
Peso 0.5: Utilização do EntityManager ou Repository específico para a inserção do Produto.

O que penaliza sua resposta?

Penalidade - 5: Criação de setter. Não é necessário dada a especificação.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller com um método para receber o post com os dados da nova categoria.
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados do novo produto. Ali também eu uso a anotação @Valid e @RequestBody para indicar a necessidade de validação e que o dado vem no corpo da requisição.
Aqui é um cadastro um pouco mais complicado, dado que o novo produto, além de informações de tipos que já existem na linguagem, como nome, quantidade, e descrição, tem também as suas características. Então, na classe que representa os dados de entrada de um novo produto, eu adiciono um atributo do tipo Set<NovaCaracteristicaRequest>. Justamente para representar os dados das características que vão chegar. Eu utilizo o Set aqui porque não queremos características com nomes iguais para o mesmo produto.
Na classe NovaCaracteristicaRequest eu tenho os atributos que representam a chave e o valor da característica. Preciso criar um equals e hashcode na classe NovaCaracteristicaRequest para que ela possa ser usada em uma coleção do tipo Set. Uso o nome como atributo que define a igualdade entre características.
Além das características, para representar a associação com a categoria, adiciono o atributo do tipo Integer/Long para receber o id categoria do produto
Adiciono as annotations de validação em cima de cada atributo. Atenção especial para a utilização do @Size para controlar o mínimo de características. E como colocamos o atributo que representa a coleção de características como uma implementação da interface Set e implementamos o equals e hashcode na NovaCaracteristicaRequest, automaticamente protegemos de nomes duplicados.
Dentro do método do controller eu converto os dados do DTO para a criação de um objeto do tipo Produto. Faço isso através de um método adicionado no DTO que retorna um Produto em função dos valores dos atributos do DTO. Aqui tem um detalhe especial. Para eu conseguir construir um novo objeto do tipo Produto eu preciso construir um outro objeto do tipo Categoria representando a categoria do produto. Preciso sair do id da categoria mãe para uma Categoria de fato. Para fazer isso eu peço um EntityManager no método de conversão, para conseguir acessar o método findById. Se quisesse usar o princípio da segregação pela interface, eu poderia receber como argumento no método de conversão do DTO uma parâmetro do tipo Function e usar um method reference para passar a referência para o método find do EntityManager. Além disso eu também preciso associar o novo produto com seu dono. Então eu também peço um parâmetro do tipo Usuario no método de conversão.
Crio a classe Produto com os atributos devidamente anotados com a Bean Validation. Um detalhe é que eu preciso receber a coleção de novas características no construtor. Eu vou decidir receber a coleção de NovaCaracteristicaRequest direto, acoplando essas camadas. O motivo dessa decisão é que decidi fazer com que a classe que CaracteristicaProduto tenha uma referência ao produto. E aí caí no dilema do ovo e da galinha… Como eu não tenho produto, como poderia criar a característica. Preciso de um jeito de receber os dados de cada característica e aí, a partir da criação do produto, criar a caractéristica em si. E não posso deixar de passar esses dados no construtor, já que o conjunto de características é obrigatório.
Para fechar o código mencionado acima, eu adiciono o método de conversão lá na classe NovaCaracteristicaRequest, para sair dos dados da característica para uma Característica de fato. Neste método eu recebo como argumento um produto :).
Faço o mapeamento na classe Produto para que o Hibernate consiga persistir os objetos. Importante aqui a utilização @OneToMany em cima do atributo do tipo Set<CaracteristicaProduto>. Aqui também é importante usar o parâmetro mappedBy no mapeamento da coleção, já que este mapeamento simplesmente é o mesmo do @ManyToOne que vai existir na classe CaracteristicaProduto.
Adiciono as annotations de validação da bean validation na classe Produto.
Crio a classe CaracteristicaProduto recebendo o nome, descricao e produto como argumento no construtor.
Faço o mapeamento para que o hibernate consiga persistir objetos do tipo CaracteristicaProduto. Importante lembrar do ManyToOne em cima do atributo do tipo Produto.
Adiciono as annotations de validação da bean validation na classe Produto.
Recebo injetado o EntityManager no controller  e gravo o produto no banco de dados.
Deixo o Hibernate criar a tabela para mim no banco de dados. Poderia ter criado também o script e executado contra o banco


## Atrelar imagens à um produto

#### Resposta

Primeiro eu crio um form do tipo Dto para a imagem. Ela vai receber uma lista de MuitpartFiles que representam arquivos diversos.
Nessa classe eu crio um método que irá receber as imagens no ProdutoController junto com o id do produto enviado no path da URL.
Eu irei buscar o produto pra validar a sua existência. Logo depois eu confirmo se aquele produto pertence àquele cliente comparando
as foreign keys.
Eu crio a entidade Imagem e uso o Hibernate pra fazer o mapeamento Objeto Relacional.
Agora, precisamos implementar um contexto no controller através do método adicionarImagem(), que usa a annotation @PathVariable("id)
e recebe o id do produto. Precisamos autenticar o usuário através do Spring Security e Jwt.
Pra isso, usamos a anotação @AuthenticationPrincipal no objeto usuario.

Procuramos o produto pra checar se ele existe. Se sim, adicionamos a imagem ao produto.

Podemos criar alguns métodos na classe de produto para ir compondo progressivamente o nosso produto.
Caso eu estivesse em um ambiente de desenvolvimento e quisesse criar/simular links ao invés de salvar
as imagens, eu poderia criar um StringBuilder pra me devolver o path original que o usuario acessou pelo
método HTTP concatenado pelo nome do arquivo "salvo" usando o método .getOriginalFilename() para concatenar
na URL de acesso.

Funcionalidade: Usuário logado adiciona imagem no seu produto

Cenário:
Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

O que seria bom ver nessa resposta?

Peso 1: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber um POST, configurado para validar entrada de dados.
Peso 1: Utilização de uma classe específica para receber os dados das novas imagens. Devidamente anotada com a validação de tamanho e com a coleção de MultipartFiles.
Peso 3: Criação de uma classe específica para realizar o envio das imagens para um serviço terceiro. Aqui pode ser uma implementação fake :).
Peso 2: Criação da interface de envio de imagens para os serviços de modo que possamos ter as implementações em funções do ambiente.
Peso 2: Lógica de associação entre as imagens do produto e o produto em si.
Peso 1: Representação dessa associação como relacionamento através do mapeamento objeto relacional.

O que penaliza sua resposta?

Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.
Penalidade: -1: Ter usado o @RequestBody no método que recebe as imagens
Penalidade: -3: Ter guardado as imagens no sistema de arquivos do próprio projeto. Guardar arquivos é um trabalho complicado… Tem permissão, controle de cache, disponibilidade daquilo para download etc. Delegamos isso para serviços específicos como o S3 da Amazon.

Resposta do Especialista:

Crio método novo no controller que adiciona produto
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados das imagens que estão enviadas. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Eu não adiciono o @RequestBody, já que no upload o formato de envio é diferente.
Crio a classe que representa os dados de entrada das imagens. Lá eu adiciono a annotation de validação @Size para garantir o tamanho mínimo. O atributo é do tipo List<MultipartFile>...
No meu caso, como sugerido no projeto, eu ainda carrego o usuário com um email fixo para simular o usuário logado. Claro que também poderia configurar o Security e receber o usuário logado como argumento do método.
Coloco como parte do endereço o id do produto que eu devo associar as imagens.
Recebo injetado o EntityManager para carregar o produto pelo id.
Carrego o produto pelo id.
Verifico se o usuário logado é realmente dono daquele produto. Caso não seja, retorno o status que indica acesso negado.


Agora preciso enviar as imagens para um lugar que saiba lidar com isso melhor que minha aplicação. Usaria um S3 da Amazon por exemplo. Mas ao mesmo tempo, por conta do desafio extra, preciso possibilitar que o upload aconteça de mentira em ambiente de desenvolvimento. Para isso faria o seguinte: criaria a interface Uploader com uma implementação inicial fake. A interface Uploader receberia a coleção de multipartfiles e retornaria uma coleção de String representando os links. Poderia ser uma coleção de URL também.


Dada a implementação acima, eu também receberia injetado uma referência para um objeto cuja classe implementasse a interface Uploader. Aqui eu quebro a linha de controller 100% coeso, mas ainda mantenho a classe enxuta e com valores de métricas de complexidade baixos.
Agora uso o objeto que faz upload e envio as imagens. Como resultado, estou com os links de destino das imagens no serviço externo.
Agora invoco um método no objeto do tipo produto que associa estes links com o produto em si.
Este método pega os links, converte para objetos do tipo ImagemProduto e guarda no atributo que representa a coleção de imagens. Poderia ser também uma coleção de Strings :).
Esta classe ImagemProduto tem um link e um atributo do tipo Produto. Além do construtor recebendo as duas informações. Além disso tem um @ManyToOne como parte do mapeamento, ali no atributo do tipo Produto.
Anoto a coleção de imagens com @OneToMany e uso o mappedBy para indicar que tal relacionamento já está mapeado por um @ManyToOne na outra classe.
Ainda no atributo que representa a coleção de imagens eu uso o cascade do tipo MERGE, para forçar a atualização na chamada do merge no EntityManager. Aqui podem existir outros approaches. Ter a coleção de Strings era uma, assim como ter a classe ImagemProduto como Embeddable em vez de entity. Claramente a da String era a mais simpels, mas usariamos uma coleção de String para representar links para imagens. Decidi que era melhor aumentar a complexidade e criar uma classe de domínio específica.
No controller, invoco o entityManager.merge em cima do produto, para forçar a atualização do produto. Aqui tem um acoplamento invisível :(. Só chamo o merge, porque o atributo com as imagens está anotado com cascade MERGE. Se tivesse usado String lá ou o Embeddable não precisaria disso. Por outro lado usando uma entidade, com uma complexidade similar a do Embeddable, eu já deixo a tabela do banco preparada para evolução. Ficar preparado para evolução sem aumentar a complexidade é ok.


## Adicionar opinião ao produto

Crio o método no ProdutoController que recebe um OpiniaoForm como argumento.
Meu form exige nota, descricao e id do produto e usuário como parâmetros obrigatórios.
O @PathVariable exige o id do produto na URI da request e é usada para associar o produto ao usuário.
Preciso usar o @Valid para que o bean validation valide as entradas de dados do cliente.
nota de 1 à 5 e descrição de no máximo 500 caracteres.
Crio a entidade e mapeio com a ajuda do Spring Data e Hibernate.
Como vários usuários podem ter várias opiniões e varias opiniões podem pertencer a um mesmo usuário,
mapeio o relacionamento de Usuario dentro de opiniões como @ManyToOne.
Usamos um método de conversão no OpiniaoForm que procura pelo produto usando o id referenciado no path
da request. Se o produto existe, atualizamos o mesmo e inserimos a opinião juntamente ao produto.
Usamos uma classe do tipo Dto para apresentar esses dados e levá-los ao cliente.
Criamos então um método de atualização do produto dentro do dto e atualizamos nossa entidade.
Nosso controller responde com um ResponseEntity.ok() e retorna um produto com uma opinião atrelada ao 
produto e ao usuário.


## Caminho cognitivo Opiniao

Funcionalidade: Adicione uma opinião sobre um produto

Cenário:
Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

O que seria bom ver nessa resposta?

Peso 1: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber um POST, configurado para validar entrada de dados.
Peso 1: Utilização de uma classe específica para receber os dados da nova opinião.
Peso 0.5 : Aqui é utilizada a annotation de validação de mínimo e máximo para garantir a nota entre 1 e 5.
Peso 0.5: Resto do código
Peso 3: Criação de um método de conversão na classe que representa os dados da opinião para criar um novo objeto do tipo Pergunta.
Método recebe um usuário e um produto para relacionar com a pergunta.
Peso 1: Criação da classe que representa a opinião.
Peso 2: Utilização do construtor para receber as informações
Peso 2 : Mapeamento da Opinião para o hibernate poder realizar a persistência. Aqui precisa ter o @ManyToOne para produto e usuário que fez a pergunta.

O que penaliza sua resposta

Penalidade - 5: Criação de qualquer setter ou getter. Não é necessário para a funcionalidade.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller novo com o método para receber a opinião
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados que estão enviados. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Também precisamos do @RequestBody para indicar que os dados vem no corpo da requisição.
Aqui é preciso ter a validação de min e max da nota
Aqui no endereço eu adiciono o id produto para acessá-lo via @PathVariable no método.
Crio a classe que representa os dados da opinião. Lá tem todas as informações necessárias. Assim como as annotations para validar a entrada.
Recebo o usuário como argumento do método indicando a necessidade com a annotation @AuthenticationPrincipal.
Recebo injetado o EntityManager para carregar o produto pelo id.
Carrego o produto em questão pelo id.
Crio um método de conversão na classe que representa os dados da opinião. Este método recebe como argumento um usuário, um produto e retorna um objeto do tipo Opiniao.
Crio a classe Opiniao com as informações necessárias, além do produto relacionado e do usuário que enviou a opinião.
Todas essa informações entram pelo construtor.
Anoto os atributos com as annotations da Bean Validation.
Faço o mapeamento necessário para que o Hibernate consiga persistir o objeto. Aqui tem atenção especial ao uso do @ManyToOne no produto e no usuário.
Uso o EntityManager para persistir a opinião no controller.


## Adiciona pergunta à um produto

Crio um controller novo com o método para receber a pergunta
O argumento do método do controller é do tipo de uma classe DTO para receber os dados que estão enviados. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Também precisamos do @RequestBody para indicar que os dados vem no corpo da requisição.
Também uso o @AuthenticationPrincipal para garantir que o usuário validado possa interagir com objetos pertencentes àquele caso.
O Controller recebe o form e então usa o parâmetro do produto no @PathVariable para buscar informações e instanciar a pergunta.
Usamos o produtoRepository para instanciar o produto e adicionar a pergunta com um método criado na classe Produto.

A nossa classe form usa o método toModel() que recebe por Dependency Injection um produtoRepository e um usuário que será identificado pela
relação com o produto ao qual receberá a pergunta. Ele checa se um produto com o id dado existe e então instancia a pertunta com o título e um usuário. A data da criação é setada automaticamente na instanciação da classe Pergunta.

Crio um método no ProdutoDto que irá adicionar a Pergunta ao ProdutoDto que irá como resposta ao cliente.
Lembrando que cada pergunta pode pertencer a um usuário e esse usuário pode ter várias perguntas atreladas ao produto, sendo assim,
usamos o @ManyToOne no campo Usuario.

Na classe Dto usamos um método para adicionar a pergunta ao produto atrelado à um cliente.
Caso a resposta seja 200 ok, crio uma classe simples para imprimir na tela uma mensagem direcionada ao dono do produto referenciando um
cliente que fez a pergunta a um determinado produto.


#### Caminho cognitivo

Funcionalidade: Um usuário logado pode fazer uma pergunta sobre o produto



Cenário:

Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.




O que seria bom ver nessa resposta?



Peso 1: Criação do método para receber a requisição com os dados de cadastro anotado com a configuração para receber um POST, configurado para validar entrada de dados.
Peso 1: Utilização de uma classe específica para receber os dados da nova pergunta.
Peso 2: Criação de um método de conversão na classe que representa os dados da pergunta para criar um novo objeto do tipo Pergunta.
Método recebe um usuário e um produto para relacionar com a pergunta.
Peso 1: Criação da classe que representa a Pergunta.
Peso 2: Utilização do construtor para receber as informações
Peso 1 : Mapeamento da Pergunta para o hibernate poder realizar a persistência. Aqui precisa ter o @ManyToOne para produto e usuário que fez a pergunta.
Peso 2: Criação da estrutura para enviar emails no sistema. Não precisa enviar email de verdade. Se enviar, ninguém vai reclamar também :P.

O que penaliza sua resposta?

Penalidade - 5: Criação de qualquer setter ou getter. Não é necessário para a funcionalidade.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller novo com o método para receber a pergunta
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados estão enviados. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Também precisamos do @RequestBody para indicar que os dados vem no corpo da requisição.
Aqui no endereço eu adiciono o id produto para acessá-lo via @PathVariable no método.
Crio a classe que representa os dados de da pergunta. Lá tem todas as informações necessárias. Assim como as annotations para validar a entrada.
Recebo o usuário como argumento do método, usando a annotation @AuthenticationPrincipal.
Recebo injetado o EntityManager para carregar o produto pelo id.
Carrego o produto em questão pelo id.
Crio um método de conversão na classe que representa os dados da pergunta. Este método recebe como argumento um usuário, um produto e retorna um objeto do tipo Pergunta.
Crio a classe Pergunta com as informações necessárias, além do produto relacionado e do usuário que enviou a pergunta.
Todas essa informações entram pelo construtor.
Anoto os atributos com as annotations da Bean Validation.
Faço o mapeamento necessário para que o Hibernate consiga persistir o objeto. Aqui tem atenção especial ao uso do @ManyToOne no produto e no usuário.
Uso o EntityManager para persistir a pergunta no controller.
Agora preciso enviar o email para o vendedor. Para isso eu crio uma classe que representa uma espécie de central de emails do sistema. Nessa classe eu adiciono o método enviaEmailNovaPergunta que recebe como argumento um objeto do tipo Pergunta.

Lá dentro eu preciso enviar o email. Para isso crio um classe que representa um enviador Fake de email com um método que recebe os parâmetros convencionais de um email. Recebo esse enviador fake de emails injetado na central de emails e disparo o email com as informações necessárias.

Claro que aqui eu também poderia ter criado a abstração necessária para deixar pronta a infra de enviar email em dev e produção.


## Detalha produto

Eu criaria um RestController com o verbo GET, usando um @PathVariable idProduto/info ou descricao...
Ele receberia somente um id que é usado pelo repository para buscar, validar e instanciar o produto.
Esse produto é passado como parâmetro dentro de um Dto de resposta onde é processada todas as informações
disponíveis no momento da requisição.
Essas informações são submetidas à métodos que extraem as informações complementares como médias, total, etc...
como também, montamos um objeto de response somente com informações relevantes e não sensíveis ao cliente.
Usamos o response entity para criar nossa response e o cabeçalho retornando o objeto Dto detalhado e um status 200 ok. 


#### Caminho Cognitivo

Funcionalidade: Escreva o código necessário para montar a página de detalhe

Cenário:
Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

O que seria bom ver nessa resposta?

Peso 1: Criação do método para receber a requisição de detalhe do produto anotado com a configuração para receber um GET,
Linhas de solução com peso 3:
Utilização de uma classe específica para retornar os dados do detalhe do produto ou montou a saída direto no método do controller. Aqui é importante receber o produto como argumento do construtor.
Montou a saída diretamente no método do controller retornando um Map por exemplo.
 Peso 3: Queries planejadas para montar um objeto com os dados necessários para serem retornados ou mapeamentos adicionados na classe Produto para acessar perguntas e opiniões relativas àquele produto.
Peso 3: Ter a lógica de cálculo de média de notas de opinião em alguma classe específica.

O que penaliza sua resposta?

Penalidade -2: Criou um método privado em algum lugar para calcular a média de notas ou fez a lógica de cálculo de média direto no construtor da classe de detalhe, método do controller.
Penalidade - 5: Criação de qualquer setter. Não é necessário para a funcionalidade.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller novo com o método para retornar os dados necessários para montar os dados de detalhe
O método é anotado com @GetMapping e mapeado para o endereço necessário
Aqui no endereço eu adiciono o id produto para acessá-lo via @PathVariable no método.
Recebo injetado o EntityManager para carregar o produto pelo id.
Carrego o produto em questão pelo id.
Já retorno no método um objeto que vai carregar os dados necessários para montar a página de detalhe do produto
Crio essa classe de saída com um construtor recebendo o produto
No construtor começo puxando as informações que são básicas
Eu preciso deixar disponível as características do produto. Para isso adiciono um método na classe Produto que me permite receber uma função que mapeia característica para qualquer coisa. Invoco esse método a partir do construtor da classe de detalhe de produto, mapeio as características para uma coleção de objetos de saída e guardo num atributo na classe de detalhe.
Eu preciso deixar disponível os links para imagens do produto. Para isso adiciono um método na classe Produto que me permite receber uma função que mapeia imagens para qualquer coisa. Invoco esse método a partir do construtor da classe de detalhe de produtos, mapeio os links para uma coleção de objetos e guardo num atributo na classe de detalhe.
Eu preciso deixar disponível as opiniões do produto. Aqui tem um detalhe a mais, porque preciso fazer cálculos em cima da coleção de opiniões. É importante saber quantas opiniões foram deixadas, assim como  a média da nota. Para isso adiciono um método na classe Produto que me retorna um objeto do tipo Opinioes. Ele tem a coleção de opiniões e métodos que fazem cálculos sobre essas opiniões. Com esse objeto na mão, eu invoco os métodos que retornam o total, média e mapeio as opiniões para as informações que precisam ser exibidas na página de detalhe do produto.
Agora eu adiciono todos os getters na classe que representa o detalhe do produto

## Finalizar compra Parte 1

Crio um controller para receber um pedido de pagamento. Esse controller é do tipo POST e recebe um PagamentoForm como parâmetro.
Nosso controller recebe requests no contexto "/compras".
O controller recebe um UriComponentBuilder para criar o path de redirecionamento.
Crio um CompraForm para receber um id de um produto, a quantidade e um Enum que representará o nosso gateway de pagamento.
O form tem um metodo de conversão para entidade que recebe um produtoRepository e um usuário para que valide o id referente ao produto (se existe
ou não), e usa os atributos de produto, usuário, gateway, quantidade e produto para gerar o objeto Compra.

Preciso também usar os dados da compra para alterar o estoque e setar o status da compra como INICIADA.

No controller usamos o UriComponentsBuilder para criar a string que irá representar o url do link de redirecionamento para o pagamento realizado no gateway. Uso um template pronto e concateno com os dados da compra dinamicamente dependendo do gatway escolhido pelo cliente.
Após a uri montada, retornamos uma String como resposta caso haja sucesso e um Http.Response.foud, que representa o código 302 de redirecionamento.


#### Caminho cognitivo

Funcionalidade: Realmente finaliza compra - parte 1

Cenário:
Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

O que seria bom ver nessa resposta?
Peso 1: Criação do método para receber a requisição com os dados de compra anotado com a configuração para receber um POST, configurado para validar entrada de dados.
Peso 1: Utilização de uma classe específica para receber os dados da nova compra
Peso 0.6 : Proteção da borda através de validação automática das informações com a bean validation
Peso 0.4 : O código em si da classe.
Peso 2.5: Lógica de abater o estoque realizada através de um método da classe Produto
Peso 1: Criação da classe que representa a Compra
Peso 1: Utilização do construtor para garantir os dados de entrada da compra.
Peso 2.5: Lógica de retorno da url do gateway feita sem if/else.
Peso 1: Reutilização do mesmo componente de envio de email referenciado na funcionalidade de mandar perguntas para enviar email para a pessoa que é dona do produto sendo comprado.

O que penaliza sua resposta?

Penalidade - 5: Criação de qualquer setter no domínio. Não é necessário para a funcionalidade.
Penalidade -2: O status inicial da compra não pode ser passado como argumento de construtor. É algo default.
Penalidade - 5: Criação de uma classe, comumente conhecida como Service para realizar a operação. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller novo com o método para começar o processamento do pedido de compra do produto
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados que estão sendo enviados. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Também precisamos do @RequestBody para indicar que os dados vem no corpo da requisição.
Um outro argumento de método aqui é o usuário logado no momento, recebido através do parâmetro anotado com @AuthenticationPrincipal.
Na classe do DTO eu tenho o id do produto que vai ser comprado e a quantidade. Além disso tem o atributo que representa o gateway de pagamento escolhido. Utilizo uma enum para representar isso. Já deixo as anotações de validação da bean validation
Recebo injetado o EntityManager no controller para carregar o produto pelo id.
Crio um método no produto para abater o estoque em função da quantidade pedida que veio na request. Este método retorna um boolean indicando se deu certo ou não. Aqui é importante já notar um possível problema de concorrência, caso duas requests cheguem ao mesmo tempo no sistema para comprar o mesmo produto.
Caso não tenha conseguido abater do estoque, o código precisa retornar o status 400 ou 422 para o cliente.
Caso tenha conseguido abater do estoque, existem algumas coisas que precisam ser feitas:
Criar uma nova compra referenciando o produto que precisa ser comprado, quantidade escolhida e gateway de pagamento.
Criar o construtor da compra para receber tais informações. Dentro do construtor tem um detalhe importante: Eu preciso acessar o preço do produto e guardá-lo diretamente na compra. Essa desnormalização é necessária para que a compra não sofra efeito de uma alteração de preço no produto.
Ainda na classe compra eu tenho um outro atributo que representa o status da compra. Ele é inicializado com o status Iniciado.
Ainda na classe compra podemos utilizar um atributo extra para gerar um código específico baseado por exemplo no UUID. Esse código vai ser único e vou referenciar na integração com o gateway de pagamento.
Agora eu mapeio a classe compra para que o Hibernate possa persistir os objetos. Aqui tem atenção especial ao @ManyToOne em cima do atributo do produto.
Salvo esta compra com o EntityManager no controller
Envio o email para o comprador através da mesma classe já criada na funcionalidade da pergunta.

## Finalizar compra Parte 2

Crio dois controllers. O primeiro representando a uri de redirecionamento da compra via gateway paypal e outra
para o pagseguro. Essas uris serão devolvidas com o id da transacao como path variable.
Meu controller recebe uma transação form que simula a resposta de um gateway para nós, agora, como cliente.
O objeto transação do tipo form recebe o id da compra e um repository para confirmar a existência daquela 
transação e para validar o dado e instanciar um objeto entidade do tipo transação utilizando enums para declaração
de compra feita com sucesso ou fracaço.
De novo ao controller, a transação e a compra validadas pelo hibernate e por nossas regras de negócio, adicionamos uma 
transação e o gateway escolhido em uma compra.
Logo, agora temos uma compra com uma transação realizada com sucesso.
Para representar nossos gateways de pagamento e serviço de nota fiscal e rankings, usamos uma API de simulação de cliente.
o OpenFeing que simula um cliente ou mesmo um server a partir da nossa API.
Dessa maneira, ao realizar uma compra, somos redirecionados para os paths dessas simulações onde iremos passar os dados
da compra e do comprador, e esse ira nós responder com os dados da compra, da transacao e do produto.

Com a nota fiscal funciona do mesmo jeito.
Como o objetivo do projeto é mais simples, simulamos o envio de email para o cliente que efetuou a compra somente imprimindo uma mensagem no console.

Tenho que usar o maven repositories para adicionar o feing à minha aplicação como inserir o path no sistema de segurança e autenticação do Spring Security, permitindo o envio e recebimento de dados pela uri dos gateways simulados.
Preciso também achar uma forma de declarar ao Spring que utilizaremos uma api de simulação de cliente/servidor.

Preciso também anotar a classe de boot da aplicação com @EnableFeignClient.


Funcionalidade: Escreva o código necessário para montar a página de detalhe

Cenário:

Descreva os passos que você planeja executar para a implementação da funcionalidade. É muito importante que você descreva aqui antes da implementação em si.

O que seria bom ver nessa resposta?

Peso 1: Criação de endpoint(s) para receber o retorno da integração com o gateway.
Peso 1: Utilização de classe(s) específica(s) para receber/receberem os dados de retorno do gateway
Peso 4: A lógica de adicionar transação tem que estar dentro da classe compra.
Peso 4: Precisa de polimorfismo para lidar com os eventos de sucesso ou de falha de transação.


O que penaliza sua resposta?


Penalidade - 5: Criação de qualquer setter no domínio. Não é necessário para a funcionalidade.
Penalidade - 5: Utilização de if/else para adicionar transação de gateways diferentes.
Penalidade - 5: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 5: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

Resposta do Especialista:

Crio um controller com dois métodos, um para receber o retorno do pagseguro e outro para receber o retorno do paypal. Aqui são retornos com parâmetros diferentes e eu prefiro criar endereços diferentes de integração. Fazendo isso eu vou ter mínimas alterações para cada novo gateway.
O argumento de cada método do controller é do tipo de uma classe no estilo DTO para receber os dados que estão enviados. Ali também eu uso a anotação @Valid para indicar a necessidade de validação. Também precisamos do @RequestBody para indicar que os dados vem no corpo da requisição. A diferença entre as duas classes que representam as entradas de retorno do gatway é a forma como recebemos os status.
A partir de agora eu preciso arrumar uma solução para adicionar transações na compra. Na borda mais externa eu consigo facilmente adicionar mais um endpoint. Mas não é necessário criar um método diferente para cada nova transação que vai ser gerada em função de um gateway diferente. Então daqui para frente, a ideia é achar uma solução que traga essa flexibilidade necessária pedida pela funcionalidade.
Para ganhar a flexibilidade basta que o método de criação de transação fique dentro da classe que representa o gateway. Ali eu consigo sair do status específico do gateway para um status de sucesso e falha geral do sistema.
Agora eu crio um método na classe compra que vai adicionar uma nova transação. Eu posso receber uma transação direto ou posso receber o retorno do gateway de pagamento e chamar um método que cria a transação em função de uma compra. Opto pela segunda, mas aí preciso extrair uma interface que defina o método de criação de transação. Faço com que os DTO''s implementem ela.
Adiciono os mapeamentos na classe compra para linkar com Transação. @OneToMany no Set de transações com mappedBy apontando para o @ManyToOne em cima do atributo do tipo compra lá na classe Transação.
Adiciono no método as proteções contra transação com id de retorno duplicada e duplicação de transação concluída com sucesso. Se isso acontecer, tem bug.
Depois da transação adicionada, é necessário fazer a simulação dos eventos de sucesso: Email, ranking e setor fiscal. Para isso, a ideia é criar uma interface que representa um evento de nova compra. Podemos usar a granularidade que quisermos. Podemos ter uma interface eventos de nova compra e uma eventos de compras que falharam. Podemos ter uma interface só com todas implementações de eventos e com um if relativo ao sucesso da conclusão de compra.
Crio uma classe para controlar a execução dos eventos de nova compra, recebo injetado um objeto daquele tipo no controller e disparo os eventos com a nova compra.

FIM
