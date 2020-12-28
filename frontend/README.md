# arrange_examination_system

## 镜像建立
```
docker build . -t 镜像名//比如：test
```

## 运行镜像
```
docker run -d -p 8080:80 镜像名 //test  注意8080是指你的电脑的运行端口 80是指镜像端口，可以变化
```
### 查看镜像
```
可以在镜像建立完成之后 docker images 查看镜像
```


### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
