This is README.md
===================

## Project Structure
Repository owns two parts:

- Frontend is placed in ./frontend

- Backend is placed in ./backend

## Commit Tips
Project has added commit-message in ./.git/hooks to inspect commit message

Commit message should refer like "Added function feature. Ref #999"

## Deploy Rapidly

1. clone repository

```shell
git clone https://se.jisuanke.com/exam-schedular/ranshaonidemeng/arrange_examination_system.git
```

2. cd ./frontend

```shell
npm install
npm run serve
```

3. open backend with intellij IDEA

**Maven reload project**

![image-20210114172849619](C:\Users\Cishenn\AppData\Roaming\Typora\typora-user-images\image-20210114172849619.png)



***And the process maybe slow, provided your network is bad.***

![image-20210114173126406](C:\Users\Cishenn\AppData\Roaming\Typora\typora-user-images\image-20210114173126406.png)

4. config environment variables in Intellij IDEA

![image-20210114173343654](C:\Users\Cishenn\AppData\Roaming\Typora\typora-user-images\image-20210114173343654.png)



environment variables: ***`DATABASE_URL=tc-mysql-0.services.infra.tree-diagram.site:10034;USER=root;PASSWORD=LMS++dev`***

![image-20210114173404309](C:\Users\Cishenn\AppData\Roaming\Typora\typora-user-images\image-20210114173404309.png)



Configure is done

![image-20210114173645264](C:\Users\Cishenn\AppData\Roaming\Typora\typora-user-images\image-20210114173645264.png)