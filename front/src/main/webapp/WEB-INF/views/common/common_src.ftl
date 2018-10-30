<meta charset="UTF-8"/>
<meta name="renderer" content="webkit|ie-stand"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<link href="${root}/style/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${root}/style/font-awesome.css">
<link rel="stylesheet" href="${root}/style/jquery-ui.css">
<link rel="stylesheet" href="${root}/style/fullcalendar.css">
<link rel="stylesheet" href="${root}/style/prettyPhoto.css">
<link rel="stylesheet" href="${root}/style/rateit.css">
<link rel="stylesheet" href="${root}/style/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${root}/style/jquery.cleditor.css">
<link rel="stylesheet" href="${root}/style/bootstrap-switch.css">
<link href="${root}/style/reset.css" rel="stylesheet">
<link href="${root}/style/style.css" rel="stylesheet">
<link href="${root}/style/widgets.css" rel="stylesheet">
<!--[if lt IE 9]>
  <script src="${root}/js/public/html5shim.js"></script>
  <script src="${root}/js/public/respond.js"></script>
  <script src="${root}/js/public/indexOf.js"></script>
<![endif]-->
<link rel="shortcut icon" href="">
<script type="text/javascript" >
	var root = '${contextPath}';
</script>

<script type="text/javascript" src="${root}/js/public/jquery.min.js"></script>
<script type="text/javascript" src="${root}/js/public/jquery.cookie.pack.js"></script>
<script type="text/javascript" src="${root}/js/crdloo/index/header.js"></script>
 <script src="${root}/js/public/sea.js" type="text/javascript" ></script>
		   <script type="text/javascript">
    //Set configuration--------------引入seajs之前需引入jQuery库，该代码可放入公有页面
    seajs.config({
   		base: root+"/js/crdloo/",	
        //base: "http://img.51coffer.com/js/crdloo/",				base表示基址寻址时的基址路径
        alias: {							//alias可以对较长的常用路径设置缩写
            //"jquery": "jquery.min.js"
        },
        charset: 'utf-8',					//charset表示下载js时script标签的charset属性
        timeout: 20000,						//timeout表示下载文件的最大时长，以毫秒为单位
        debug: false						//debug表示是否工作在调试模式下
    });
</script>
<script type="text/javascript" src="${root}/js/public/echarts.js"></script>