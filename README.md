# Lexical-Analyzer-1
Simple Lexical Analyzer

Create a new programming language and it define its lexicon.
For each class below, locate the tokens that will be part of language and give it a regular definition.

- Delimiters (white Spaces, tab, carriage return) : ws -> (blanck, tab, newline)⁺

![Delimiters img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/Delimiters.jpg?raw=true)

- Keywords (if then else while for): if-> i f | then-> t h e n | else-> e l s e | while-> w h i l e | for-> f o r

![Key img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/If.jpg?raw=true) (only if)

- Identifiers: id-> letter_(letter_|digit)*

![id img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/id.jpg?raw=true)

- literal (Digits): digit->[0-9], digits-> digit digit*

![digits img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/Digits.jpg?raw=true)

- Separators (round and square brackets, comma, semicolon): 

![digits img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/Separatori.jpg?raw=true)

- Operators ("<--" To assign and only relational operators)

![digits img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/Relop.jpg?raw=true)

Automaton referred to the function FindToken(String testo).

![digits img](https://github.com/Francesco182g/Lexical-Analyzer-1/blob/master/Lexer.jpg?raw=true)

ITALIANO:
Semplice Analizzatore Lessicale

Creare un nuovo linguaggio di programmazione e definirne il lessico.
Per ogni classe sotto riportata, individuare i token che faranno parte del linguaggio e darne una definizione regolare:

- Delimitatori (spazi bianchi, tab, ritorno a capo)
- Parole chiavi ( if then else while for )
- Identificatori
- Letterali (solo i numeri)
- Separatori ( parentesi tonde e quadre, virgola, punto e virgola)
- Operatori (“<--” per fare assegnazione e solo operatori relazionali)

Per capire il programma si faccia riferimento all'ultimo automa che rappresenta la funzione findToken(String testo).
