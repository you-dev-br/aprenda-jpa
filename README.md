Aprenda JPA
===========
Material para aprender Java JPA (Jakarta Persistence) com Spring Boot e Spring Data JPA.

Requerimentos:
* Conhecimentos basicos de Java
* Git

Tech stack utilizada neste material:
* Java 21
* JPA, também conhecido como Jakarta Persistence
* Spring Boot
* Spring Data JPA
* Maven
* Database H2

Proposta
--------
Vamos desenvolver um sistema de empréstimo de itens no qual pessoas poderão pegar emprestados vários itens e devolvê-los posteriormente.
Para facilitar a identificação dos itens, cada um deles pode ter um QR Code.
Itens semelhantes podem estar associados a uma categoria.

```text
          ┌─|PESSOA|───┐
          │            │
          │nome        │
          │email       │
          └──────────┬─┘
                     │UmParaMuitos
                     │
                     │
          ┌─|ITEM|───┴─┐                    ┌─|CATEGORIA|─┐   
          │            │MuitosParaMuitos    │             │
          │nome        ├────────────────────┤nome         │
          │descricao   │                    └─────────────┘
          └──────────┬─┘                    
                     │UmParaUm
                     │
                     │
          ┌─|QrCode|─┴─┐
          │            │
          │codigo      │
          └────────────┘
```

Videos
------
- Tutorial JPA 01 - Criar e configurar o projeto
- Tutorial JPA 02 - Salvar e buscar por chave primária
- Tutorial JPA 03 - Relacionamento UmParaUm (@OneToOne)
- Tutorial JPA 04 - Relacionamento UmParaMuitos (@OneToMany)
- Tutorial JPA 05 - Relacionamento MuitosParaMuitos (@ManyToMany)
- Tutorial JPA 06 - Cascade Type
- Tutorial JPA 07 - Busca pelo nome do método
- Tutorial JPA 08 - Busca pela @Query
- Tutorial JPA 09 - Busca pela Criteria API
- Tutorial JPA 10 - Busca por exemplo
- Tutorial JPA 11 - Paginação
- Tutorial JPA 12 - Ordenação
- Tutorial JPA 13 - Fetch Type: Lazy vs Eager 
- Tutorial JPA 14 - @Transactional
- Tutorial JPA 15 - @JoinTable e @JoinColumn
- Tutorial JPA 16 - Colunas em formato Json
- Tutorial JPA 17 - @Embedded: Entidade embutida
- Tutorial JPA 18 - Relacionamentos com attributos

Desenvolvimento
---------------

Você pode rodar os testes pelo seu editor favorito ou pelo maven com o seguinte comando:
```bash
./mvnw
```

Vamos utilizar um banco de dados H2 rodando em memória que será inicializado toda vez que executarmos os testes.

Referencias
===========
* [String Initialzr](https://start.spring.io/)
* [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/reference/)
* [Wikibooks Java Persistence](https://en.wikibooks.org/wiki/Java_Persistence)
