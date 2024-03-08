# Tutorial 15 - @JoinColumn e @JoinTable

### SQL Gerado
Por padrão, o JPA gera o seguinte SQL:
```
    create table item (
        id integer not null,
        qr_code_id integer unique,
        descricao varchar(255),
        nome varchar(255) not null,
        primary key (id)
    )
    create table pessoa (
        id integer not null,
        email varchar(255),
        nome varchar(255),
        primary key (id)
    )
    create table pessoa_items (
        items_id integer not null unique,
        pessoa_id integer not null,
        primary key (items_id, pessoa_id)
    )
```

O SQL gerado pode ser visualizado como o seguinte diagrama:
```
          ┌─|PESSOA|───┐
          │id          │
          │nome        │
          │email       │
          └──────────┬─┘
                     │
          ┌─|PESSOA_ITEMS|──┐
          │items_id         │
          │pessoa_id        │
          └──────────┬──────┘
                     │
          ┌─|ITEM|───┴─┐   
          │id          │
          │nome        │
          │descricao   │
          └────────────┘
```

### @JoinColumn
Se queremos representar o relacionamento com uma coluna podemos utilizar o `@JoinColumn`.
Portanto, temos que atualizar a nossa classe Pessoa:
```java
@OneToMany
@JoinColumn(name = "emprestado_para_pessoa")
private Set<Item> items = new HashSet<>();
```

Que vai resultar no seguinte relacionamento:
```
          ┌─|PESSOA|───┐
          │id          │
          │nome        │
          │email       │
          └──────────┬─┘
                     │
          ┌─|ITEM|───┴──────────────┐   
          │id                       │
          │nome                     │
          │descricao                │
          │emprestado_para_pessoa   │
          └─────────────────────────┘
```

### @JoinTable
Se queremos representar o relacionamento com uma tabela intermediaria podemos utilizar o `@JoinTable`.
Portanto, temos que atualizar a nossa classe Pessoa:
```java
@JoinTable(
        name = "emprestimos",
        joinColumns = @JoinColumn(name= "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name="item_id"))
private Set<Item> items = new HashSet<>();
```

Que vai resultar no seguinte relacionamento:
```
          ┌─|PESSOA|───┐
          │id          │
          │nome        │
          │email       │
          └──────────┬─┘
                     │
          ┌─|PESSOA_ITEMS|──┐
          │items_id         │
          │pessoa_id        │
          └──────────┬──────┘
                     │
          ┌─|ITEM|───┴─┐   
          │id          │
          │nome        │
          │descricao   │
          └────────────┘             
```
