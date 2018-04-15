# 中山大学软件工程中级实训Part1自学报告

#### 数据科学与计算机学院
#### 梁育诚
#### 16340133

## Vi/Vim
&emsp;&emsp;大一的时候在上程序设计课程的时候就已经接触过Vim这款编辑器，它是一款Linux系统下的常见代码编辑器，可以支持直接在命令行中编辑文本，但是它的界面相比起其他编辑器如sublime text或者Atom等，没有相关的语法高亮，同时，Vim也不支持鼠标的点击、括号的补全和换行缩进，因此从方便性来说个人不是很喜欢使用Vim。

&emsp;&emsp;但是在某些时候，Vim的作用还是很明显的。比如说在Part1配置环境变量的时候，我需改进入一些文档去添加路径，这个时候使用命令行+Vim就很方便地直接修改，而不需要说打开图形化的Sublime修改后再保存。

&emsp;&emsp;直接在命令行中输入`vim`可以打开Vim编辑器编辑代码，或者`vim filename`来直接打开一个已经存在的代码文件。编辑完成后，先按`Esc`，再输入`:wq`保存退出。

&emsp;&emsp;命令行打开和退出Vim的操作：
1. 在命令行直接输入`vim`，进入Vim编辑器界面
![vim打开](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/vim1.png)
2. 在vim中编写代码
![vim编写代码](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/vim2.png)
3. 编辑结束后，先按`Esc`，再输入`:wq`保存退出。
![vim保存退出](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/vim3.png)

## Java
&emsp;&emsp;本次实验的云桌面已经配好了java的开发环境，因此不需要自己配置。在大一的寒假，我自学了一部分java的知识，在windows平台上也配置过java的开发环境，主要分为两步：

  + 上官网下载JDK
  + 配置系统的环境变量
windows下的配置过程有兴趣了解的话可以访问我的博客https://leungyukshing.github.io/archives/Set-up-Java-Environment.html。

&emsp;&emsp;为了更好地学习java，我一直使用的是命令行编译的模式来运行代码，主要分为两步（以HelloWorld.java为例）：
```
javac HelloWorld.java
java HelloWorld
```
&emsp;&emsp;对于java的学习和更为详细的笔记可以参考我的博客：https://leungyukshing.github.io/tags/Java/

&emsp;&emsp;需要注意的是文件名必须和主类的名字相一致，否则JVM会因为找不到程序入口而报错。

&emsp;&emsp;在java语法方面，因为有C的基础，所以大多数的语法是没有问题的。所以入门的难度不大。然而两者之间还是有一定的区别。Java的package、interface、GC是相比起C++而言更具优势的地方，在设计的结构和形式上会有略微的不同，但总的来说相差不大。

&emsp;&emsp;这是我的calculator小程序的运行截图：
![calculator运行截图](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/calculator.png)

## Ant
&emsp;&emsp;同样地，Ant也是云平台已经安装并配置好的，不需要自己配置。如果需要自己配置，也可以直接通过包管理的方法，在命令行输入
```
sudo apt-get install ant
```
来进行安装。

&emsp;&emsp; Ant主要有两大功能：
① 对制定文件或目录的删除、移动、创建
② 部署编译java程序

&emsp;&emsp;它的语法主要有几点：

&emsp;&emsp;① project: 每个project是一个大的任务。每个`build.xml`文件中至少含有一个project。其中`name`属性是project的名字，`default`属性是这个project默认执行的`target`，`basedir`属性是指整个项目中文件路径的基地址。

&emsp;&emsp;② target：每个target更像是一个步骤，可以自己为这个步骤命名，用于project的唯一识别。当然还有最重要的depends，意思就是执行当前的这个target要依赖于depends中的target的执行。

&emsp;&emsp;③ property：`property`有点类似于C++中的宏定义，可以用一些我们便于理解的变量名来代替复杂的路径，提高代码的可读性。

&emsp;&emsp;这次实训我主要用到的是`clean`,`mkdir`,`javac`,`java`,`junit`这几个功能。

&emsp;&emsp;执行的方法很简单，在一个项目文件下创建自己的ant文件，一般命名为`build.xml`，然后在命令行中输入`ant`命令就可以了。在自学的过程中，我尝试用ant命令编译和运行`HelloWorld.java`，但是一直都只是编译成功，在控制台没有输出"HelloWorld"，后来自己检查了一下`build.xml`文件后发现，是`project`中的**default**写错了。这里的**default**可以简单地理解为我最终要做的事情，通过找到名为**default**属性字段的**target**，根据其**depends**依赖关系，一层一层地往上找。因此，我就需要把最后一步运行的命令设置是**default**，修改完成后运行成功。

为`HelloWorld.java`编写了测试类，编写`build.xml`，最后运行`ant`命令。
`build.xml`分析
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="AllTest" default="junit" basedir=".">

    <!-- 源代码src路径 -->
    <property name="src.path" value="src/java"/>
    <!-- 编译文件.class路径 -->
    <property name="build.path" value="build"/>
    <!-- 测试代码路径 -->
    <property name="test.path" value="src/test"/>
    <!-- junit包路径 -->
    <property name="lib.path" value="lib"/>
    <!-- report路径 -->
    <property name="report.path" value="report"/>

    <!-- 设置classpath -->
    <path id="compile.path">
        <fileset dir="${lib.path}">
            <include name="**/*.jar"/>
        </fileset>

        <pathelement path="${build.path}"/>
    </path>

    <!-- 清除历史编译.class -->
    <target name="clean" description="clean">
        <delete dir="${build.path}"/>
    </target>

    <!-- 编译测试文件，初始化build目录 -->
    <target name="compile" depends="clean">
        <mkdir dir="${build.path}"/>
        <javac srcdir="${src.path}" destdir="${build.path}"  classpathref="compile.path" includeantruntime="on"/>
        <javac srcdir="${test.path}" destdir="${build.path}"  classpathref="compile.path" includeantruntime="on"/>
    </target>

    <!-- 执行测试案例 -->
    <target name="junit" depends="compile">
        <junit printsummary="true">
        	<formatter type="xml" usefile="true"/>
             <classpath refid="compile.path"/>

             <test name="HelloWorldTest" todir="${report.path}"/>
         </junit>
     </target>

     <!-- 清除报告 -->
     <target name="deltefile">
     	<delete dir="${report.path}"/>
     </target>
</project>
```
&emsp;&emsp;其中，对`property`的设置是为了便于下面的代码书写路径。设置了一个名为**compile.path**的path路径，意思是当有属性使用到这个路径时，它将包含两部分：一是`fileset`设定的在`lib`下的一类文件；二是`build`目录下的文件。这样设置的好处是，当后面需要使用到测试类的时候，可以顺利地编译和运行，而无须在多个地方重复include同样的文件。同时，我在`junit`中添加了输出测试报告，这样就可以通过查看测试报告了解到详细的错误信息。

&emsp;&emsp;值得注意的是，当我为`EasyCalculator.java`编写`build.xml`时，在`java`的部分要加上`fork="yes"`，这样才能成功运行GUI程序。

这是测试`HelloWorld`的运行截图：
![](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/ant.png)

## Junit
&emsp;&emsp;相比起Ant的学习，Junit的自学比较简单。首先我说一下我对Junit的理解。它用于项目的单元测试。之前我们写的测试类有很大的麻烦，一是测试类必须继承TestCase，二是测试的方法必须以test开头，对于一个比较大的类而言，编写一个测试类是很麻烦的，因此我们使用Junit。Junit允许程序员使用`@Test`来描述测试的数据。我们可以使用`assertEquals()`对输出结果进行判定。如果符合预期输出，则输出true，否则报错。

&emsp;&emsp;云平台也是已经配置好了Junit的环境，但是在实际测试的时候，需要把压缩包拉进测试项目的根目录下，并且在编译和运行测试类的时候，都需要把路径标出来。
```
javac -classpath .:junit-4.10.jar HelloWorldTest.java
java -classpath .:junit-4.10.jar -ea org.junit.runner.JUnitCore HelloWorldTest
```

&emsp;&emsp;同时我也可以将junit写在`build.xml`中，使用`ant`命令一次过实现对一个程序的编译和测试。
这是我使用ant和junit对`HelloWorld.java`测试的结果：
![](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/junit.png)

## Sonar
&emsp;&emsp;虽然没有要求，但是我也想把Sonar的学习写入报告。作为一个用于代码质量管理的开源平台，Sonar可用作对代码的管理和优化。自己刚学java的时候虽然很多时候写的东西在编译上没有报错，但实际上可能结构不是那么好，或者说有很多冗余的东西，使用Sonar就可以检测出来。同时也可以用Sonar规范代码风格、优化复杂度、添加注释等。

&emsp;&emsp;在配置Sonar服务的环境变量时，通过修改`~/.bashrc`将环境变量添加进去。命令行输入：
```
vim ~/.bashrc
```
然后添加变量：
![sonar环境变量](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/sonar2.png)
然后在命令行加载变量，完成配置：
```
source /etc/profile
```
&emsp;&emsp;Sonar是一项服务，这意味着我们每次使用它的时候都需要启动服务，命令有三条：
```
./sonar.sh start   启动服务
./sonar.sh stop    停止服务
./sonar.sh restart 重启服务
```

&emsp;&emsp;然后我们就可以编写`sonar-project.properties`配置文件，文档中给出了格式，只需要将sonar.projectKey, sonar.projectName和java-module.sonar.projectBaseDir三项的值均改为要测试的文件夹名称即可。随后在命令行中输入：
```
sonar-runner
```

&emsp;&emsp;我对于自己编写的`calculator.java`代码进行了检测。第一次检测的分数不高，major错误也有几个，其中有变量命名的风格问题，也有单个函数复杂度过高的问题。经过仔细修改后，包括优化了一些冗余的判断语句等，成功地将major全部修复。最终的测试结果如下：

命令行显示
![sonar运行](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/sonar1.png)

打开`http:\\localhost:9000`的显示
![sonar测试结果](https://raw.githubusercontent.com/leungyukshing/GridWorld/master/Part1/Images/sonar.png)

# 小结
&emsp;&emsp;首先我概要地说一下我对Ant, Junit, Sonar的理解。
  + Ant是一个基于java的**生成工具**，它可以简化我们每次编译所需要输入的命令，根据每条命令之间的**依赖关系**，我们就可以通过编写一个`build.xml`文件来生成一个可执行的java程序。
  + Junit是一个java的单元测试框架，它所做的测试是**白盒测试**，也就是通过比对输入来确定程序的正确性。它提供给程序员高效的测试框架。可以结合Ant使用。
  + Sonar是一个代码质量管理系统，它可以从多个维度对一份代码进行各方面的分析，便于程序员对代码的风格、结构、算法复杂度等进行优化。


&emsp;&emsp;在不到一天之内完成了Ant, Sonar, Junit的配置和使用，也完成了检查，我对自己的学习能力还是很满意的。从一无所知，到有所了解，中间经过了不少的弯路。个人得出的经验是，首先是要多看例子，尤其是像写`build.xml`这样，上网多看一些别人写的例子，逐句理解，看多了就自然会写了。第二就是要细心，学习新知识的时候因为充满着未知，又因为有时间的限制，心情自然会有点急，但是看文档、看代码的时候要静下心来，不要错过每一个符号，就像路径中的`.`，漏了就有可能会导致整个编译的错误。第三就是在日后一定要善于运用好这些工具，就像Sonar这个服务，我觉得就很有用。一开始使用它来分析自己的一份代码，没想到一份简单的代码都被找出这么多可以改进的地方，这就说明自己平常的编程习惯还有待改进。总的来说收获很丰富，希望自己接下来能学到更多有用的东西。
