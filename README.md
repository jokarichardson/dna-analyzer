# Analisador de Cadeias de DNA - Teste Símios Meli

<p align="center"><img src="https://github.com/jokarichardson/dna-analyzer/blob/master/src/main/resources/dna.jpg" width="212" height="142" /></p>

Em um futuro distante, na cadeia de evolução, os símios e os humanos estão cada vez mais próximos. 

Por esse motivo ficou muito difícil distinguir quem é humano e quem é símio.

Com isso, fora elaborada solução de análise de Cadeias de DNA, a fim de permitir ou não o acesso ao sistema mediante a condição de ser ou não símio.

Caso seja identificada mutação na cadeia de DNA informada, será permitido o acesso, com retorno de status 200
Em sendo identificado DNA humano, não será permitido o acesso, com retorno de status 403.

## Execução em Nuvem

Acessar a URL https://dna-analyzer.herokuapp.com/, via Postman ou via navegador.
Em sendo acessada via navegador, será apresentada a interface do Swagger com as operações disponíveis.

## Avaliação de Cadeias de DNA

1. Dada uma determinada sequência de cadeias de DNA, com o formato JSON abaixo:

```javascript
{
  "dna": ["TCTTAC", "CTTGAC", "TGATCT", "GTATGG", "GCTCTA", "TCAGTG"]
}
```

2. Ao ser chamado o endpoint <b>/simian</b>, via método <i>POST</i>, a aplicação avaliará se se trata de humano ou símio:
```python
POST https://dna-analyzer.herokuapp.com/simian
```

3. Respostas de sucesso esperadas:
- Caso seja DNA mutante, será apresentada resposta com HttpStatus 200 - Ok;
- Caso seja DNA humano, será apresentada resposta com HttpStatus 403 - Forbidden.

4. Respostas de erro esperadas:
- Caso seja DNA inválido, será apresentada resposta "A cadeia informada não possui somente bases nitrogenadas A C G T.", com HttpStatus 400 - Bad Request;
- Caso a sequência de cadeias de DNA não formem uma matriz quadrada, será apresentada resposta "A cadeia informada não gerará uma matriz quadrada NxN.", com HttpStatus 400 - Bad Request;
- Caso a sequência de cadeias de DNA possua dados em branco, será apresentada respota "A cadeia possui sequencia em branco.", com HttpStatus 400 - Bad Request.

## Estatísticas de DNA

1. Para verificar as estatísticas de análise de cadeias de DNA, com a proporção de mutantes x humanos, execute uma chamada ao endpoint <b>/stats</b>, via método <i>GET</i>:
```python
GET https://dna-analyzer.herokuapp.com/stats
```

2. Será retornada estrutura JSON abaixo:
```javascript
{
  "count_mutant_dna": 4,
  "count_human_dna": 8,
  "ratio": 0.5
}
```
