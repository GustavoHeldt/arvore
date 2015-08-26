# Detalhes de implementação #

  * O nó raiz da árvore é o filho esquerdo do atributo _'raiz'_ da classe _ArvoreBP_;
  * Ao inserir um nó na árvore, sempre é adicionado filhos com chave _null_ a esse novo nó;
  * Por consequência, o método _'buscar'_ sempre retorna um nó. O que determina se esse nó de fato está na árvore, é o atributo _'chave'_ diferente de _null_;