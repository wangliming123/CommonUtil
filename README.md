# CommonUtil
常用工具文件

## dimenUtil
android屏幕适配dimen生成工具

### GenerateDimenFile 
	生成默认dimen文件，位于value文件夹下，规格：1920*1080, dpi 480, density 3.0, sw360
	
### GenerateMoreDimens.java
	生成适配dimen，位于res文件夹下，规格（sw280-sw1000），共19个

### 如何使用
	javac GenerateDimenFile.java
	java GenerateDimenFile
	javac -encoding utf-8 GenerateMoreDimens.java
	java GenerateMoreDimens
	values文件夹下dimens.xml文件覆盖android项目res/values中的dimens.xml
	res文件夹下所有文件夹粘贴到android项目res文件夹下