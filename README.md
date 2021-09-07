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

