# 📰 Licitagov
 
<div style="margin: auto; width:70%">
    <img style="max-width: 100%" src="cover-image.png" alt="exemplo imagem">
</div>

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [X] Implementação H2
- [X] Autenticação && Autorização
- [X] Consumo API - GOV
- [X] Persistência dos dados da API
- [X] Leitura e disponibilização dos dados
- [X] Liberação CORS

## 📃 Documentação

Documentação ficará disponível após a subida da aplicação em <a href="http://localhost:8080/swagger-ui/index.html"> Documentação </a>

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Você precisará de:  
*  `Java 17` ☕ 
*  `Maven`   🐦

## 🚀 Instalando a aplicação

Para instalar o app, siga estas etapas:


```
mvn wrapper:wrapper
```

```
./mvnw clean install
```

## ▶️ Subindo a aplicação

OBS: O starting da aplicação pode ser demorado ou até dar erro. Durante o up é feita uma consulta em uma api externa e persistido os dados no banco. 

Caso de erro no Up, basta tentar novamente. 

A consulta dos dados persistidos na aplicação pode ser feito utilizando de base a data atual, retirando 1 ano e 1 dia. 

Exemplo: Data atual: 20230623 -> 20220622



Para usar Licitagov, siga estas etapas:

```
./mvnw spring-boot:run -D"spring-boot.run.jvmArguments"="-Dspring.profiles.active=develop"
```


## 🤝 Colaboradores

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/62296308?s=400&u=d0d234f9342f71e91bdcf7b8cf6f4a257302546a&v=4" width="100px;" alt="Foto do Antonio Junior no GitHub"/><br>
        <sub>
          <b>Antonio Junior</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
