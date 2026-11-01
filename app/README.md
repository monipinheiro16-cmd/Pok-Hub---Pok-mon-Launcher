# Poké Hub — Fire TV

Projeto Android nativo em Java para Fire TV/Android TV. O app foi feito para navegar com controle remoto e tem:

- Pokédex pesquisável com dados locais de exemplo
- Batalhas por turnos com cálculo de dano, PS e vitórias
- Pokémon companheiro selecionável
- Coleção e conquistas salvas em `SharedPreferences`
- Mapa interativo de regiões
- Efeitos sonoros locais com opção de desligar
- Atalhos para apps que o Android/Fire OS expõe ao aplicativo

## Gerar APK sem computador usando GitHub

1. Extraia o ZIP.
2. Crie um repositório vazio no GitHub (pode ser privado).
3. Envie o conteúdo desta pasta para a raiz do repositório, incluindo a pasta `.github`.
4. Abra **Actions** no repositório e execute **Build Poké Hub APK** (ou faça um push na branch `main`).
5. Quando terminar, abra a execução e baixe o artefato `PokeHubFireTV-debug-apk`.
6. Extraia o artefato e instale `app-debug.apk` na Fire TV usando um método de instalação de APK compatível com seu dispositivo.

## Observações importantes

- Este é um APK de depuração, não assinado para distribuição comercial.
- O projeto não substitui automaticamente o launcher padrão da Fire TV. Ele aparece como aplicativo e, em modelos compatíveis, na lista de apps de TV.
- A Fire OS pode limitar a enumeração/abertura de outros aplicativos.
- A Pokédex inicial é um conjunto de dados local ilustrativo. Imagens oficiais não são incluídas; pode-se adicionar arte própria ou conteúdo autorizado.
- Batalhas, coleção, conquistas e mapa são funcionalidades locais do protótipo; não conectam a jogos Pokémon oficiais.
- Requer Android SDK 35 e Java 17 para compilação pelo workflow.
