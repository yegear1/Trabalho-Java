# Trabalho-Java
Trabalho avaliativo de Programação Orientada a Objetos em Java cujo propósito se dá na construção/simulação de como uma farmacia funciona.

### Diagrama de Classes

```
Classes:

1. Medicamento
	// Atributos: 
		- nome: String;
		- quantidade: int;
		- preço: float;
		- descrição: String;
		- generico: boolean;

   	// Métodos:	
		+ getters/setters();
		+ compra();
		+ compramenor0();
		+ semEstoque();
		+ entradaInv();
		+ receita();
		+ adicionarEst();
		+ atualizarQnt();

2. Farmacia
   	// Atributos:
		- nome: String;
		- cnpj: int;

   	// Métodos: 
		+ menu();
		+ limpatela();
		+ wait();

3. tarjaPreta
   -> Herda de Medicamento

   	// Atributos adicionais:
		- receita: boolean;

   	// Métodos:
		+ getters/setters();

4. tarjaVerm
   -> Herda de Medicamento

   	// Atributos adicionais: 
		- receitaDupla: boolean;
		- receita: boolean;

    	// Métodos:
		+ getters/setters();

5. Funcionário
	// Atributos:
		- nome: String;
		- cpf: int;
		- cargo: String;
		- salario: float;

	// Metodos:
		+ getters/setters();

6. Farmaceutico
   -> Herda de Funcionário

   	// Atributos adicionais: 
		- formação: String;

    	// Métodos:
		+ getters/setters();

```
