# 📝 DraftPad

Bem-vindo à documentação da API do DraftPad, um bloco de notas digital desenvolvido utilizando o Framework Spring em Java. Esta API permite que você crie, leia, atualize e delete notas, proporcionando uma interface robusta para gerenciar suas anotações de forma eficiente.

## 🧑‍💻 Desenvolvedor

- [Schaidt Patriota](https://www.linkedin.com/in/schaidt-patriota-ab8b13202/)

## 📜 Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).

## 💻 Tecnologias Utilizadas

O DraftPad API é construído utilizando uma combinação de tecnologias modernas para garantir um sistema eficiente e seguro para o gerenciamento de suas notas. Aqui estão as principais tecnologias empregadas:

- **Java**: A linguagem de programação robusta e versátil que serve como base para o desenvolvimento da aplicação.

- **Spring Framework**: Um poderoso framework para desenvolvimento de aplicativos Java. No DraftPad API, ele oferece estruturas para criação de APIs REST, gerenciamento de dependências e controle transacional.

- **MongoDB**: Um banco de dados NoSQL amplamente utilizado para armazenamento e gerenciamento eficiente de dados.

- **JWT (JSON Web Token) Authentication**: Um método seguro de autenticação que permite a troca de informações entre as partes de forma criptografada. No DraftPad API, o JWT é usado para autenticar usuários e proteger endpoints.

- **Swagger**: Uma ferramenta que auxilia na documentação e teste de APIs. No DraftPad API, o Swagger é empregado para criar uma documentação interativa e amigável, permitindo que os desenvolvedores compreendam e testem facilmente os endpoints disponíveis.

Essas tecnologias foram escolhidas para proporcionar um ambiente de desenvolvimento sólido e seguro, garantindo que você tenha uma experiência confiável ao utilizar o DraftPad API para gerenciar suas notas.

## ⚙ Requisitos

Antes de executar o projeto, certifique-se de ter os seguintes programas e tecnologias instalados em sua máquina:

- Java JDK 8 ou superior
- Maven
- MongoDB

## 🔧 Instalação

Para configurar e executar o projeto em Java, siga as etapas abaixo:

1. Clone este repositório em sua máquina local:
   ```sh
   git clone https://github.com/SchaidtP/DraftPad.git
   ```

2. Navegue para o diretório do projeto:
   ```sh
   cd DraftPad
   ```

3. Certifique-se de que você tem o Java Development Kit (JDK) instalado em sua máquina.

4. Configure as informações do banco de dados e outras configurações no arquivo de configuração apropriado (por exemplo, `application.properties` se estiver usando o Spring Boot).

5. Navegue até o diretório onde está localizado o arquivo `DraftPadApplication.java`:
   ```sh
   cd src/main/java/br/com/draftpad/
   ```

6. Compile e execute o projeto:
   ```sh
   javac DraftPadApplication.java
   java DraftPadApplication
   ```

Agora o projeto está configurado e em execução. Certifique-se de ter as dependências necessárias instaladas e as configurações adequadas para que o projeto funcione corretamente.

## 📚 Utilização do Sistema

Após concluir as etapas de instalação, você estará pronto para utilizar o sistema.

Para começar a usar o sistema, siga estas etapas:

1. **Acesso ao Sistema**: O sistema já vem com um usuário com a função de ADMIN, com o nome de usuário "admin" e a senha "admin". No entanto, é altamente recomendado que você edite esse usuário ou crie um novo usuário com privilégios adequados.

2. **Acesse a Documentação**: Você pode explorar a documentação da API no [Swagger UI](http://localhost:8082/swagger-ui/index.html). Isso fornecerá informações detalhadas sobre os endpoints disponíveis, suas entradas e saídas.

3. **Explore os Endpoints**: Utilize os endpoints disponíveis para gerenciar usuários e notas. Isso inclui:

   - **Usuários**: Cadastro, login e gerenciamento de informações de usuário.
   - **Notas**: Criação, leitura, atualização e exclusão de notas.
   - **Perfil do Usuário**: Visualização das informações do perfil do usuário.

   Utilize as respostas e solicitações fornecidas pela API para interagir com o sistema de bloco de notas.

## 🌐 Documentação da API

A documentação completa da API está disponível no [Swagger UI](http://localhost:8082/swagger-ui/index.html) após a execução da aplicação.

Explore a documentação para obter informações detalhadas sobre os endpoints disponíveis, seus parâmetros e respostas. O Swagger UI oferece uma maneira interativa de entender e testar cada aspecto da API, o que torna mais fácil e eficiente a integração e o uso do DraftPad.

## 🤝 Contribuição

Se você está interessado em contribuir para a API 📝 DraftPad, nós apreciamos seu interesse em melhorar o projeto! Siga as etapas abaixo para contribuir de maneira eficaz:

1. Faça um fork deste repositório.
2. Crie um branch para a sua feature ou correção: `git checkout -b minha-feature`.
3. Realize as modificações desejadas e faça commit das alterações: `git commit -m "Minha feature incrível"`.
4. Envie as alterações para o seu repositório remoto: `git push origin minha-feature`.
5. Abra um Pull Request neste repositório, descrevendo as modificações propostas.

Agradecemos desde já a sua contribuição!

## ☎️ Contato

Se surgir qualquer dúvida, sugestão ou se você precisar de suporte relacionado à API 📝 DraftPad, sinta-se à vontade para entrar em contato com o desenvolvedor:

- LinkedIn: [Schaidt Patriota](https://www.linkedin.com/in/schaidt-patriota-ab8b13202/)

Estou aqui para ajudar e responder a quaisquer perguntas que você possa ter.
