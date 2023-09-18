Aprenda JPA
===========
Material para aprender Java JPA (Jakarta Persistence) com Spring Boot.

Requerimentos:
* Conhecimentos basicos de Java
* Java Development Kit (JDK) 19 
* Git

Tech stack utilizada neste material:
* Java 19
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
- Tutotial JPA 01 - Criar projeto e configuracoes
- Tutotial JPA 02 - Salvar e buscar por chave primaria
- Tutotial JPA 03 - Relacionamento UmParaUm (@OneToOne)
- Tutotial JPA 04 - Relacionamento UmParaMuitos (@OneToMany)
- Tutotial JPA 05 - Relacionamento MuitosParaMuitos (@ManyToMany)
- Tutotial JPA 06 - Cascade Type
- Tutotial JPA 07 - Fetch Type: Lazy vs Eager 
- Tutotial JPA 08 - @Transactional
- Tutotial JPA 09 - Busca avancada: Pelo Nome do Metodo
- Tutotial JPA 10 - Busca avancada: @Query
- Tutotial JPA 11 - Busca avancada: Criteria API
- Tutotial JPA 12 - Relacionamentos com attributos
- Tutotial JPA 13 - @JoinTable e @JoinColumn
- Tutotial JPA 14 - Pagination

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
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [Wikibooks Java Persistence](https://en.wikibooks.org/wiki/Java_Persistence)



sed -i 's/STRING_TO_REPLACE/STRING_TO_REPLACE_IT/g' filename

sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial03Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial09Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial05Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial06Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial11Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial02Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial07Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial04Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial08Test.java
sed -i 's/Aula/Tutorial/g' src/test/java/aprenda/jpa/Tutorial10Test.java
