### DocSea #### README  SERVER#

### How do I get set up? ###


1. Open project as Maven Project. (auto-import enable)

2. Create database name 'docsea'  (edit application.yml file's to change password)
				
3. Run server
        

Folder Structure :


```
#!java

org.itglance.docsea
├── src                               
	├── │java                         
   			├── domain  (entity class)
			├── repository	(jpa repository)
			├──service     (service with business logic)
				├──dto		(DTO)
			├──web
				├──rest   (Controller)
```
