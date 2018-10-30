/**
 * Created by chen on 2015/3/6.
 */
window.alert=show;
window.confirm=myConfirm;
var alertObj = new Object();
var confirmObj = new Object();
var generalStyle = {//弹窗的样式
    width: "300px",
    margin:"0",
    padding:"0",
    fontSize:"14px",
    fontFamily:"微软雅黑",
	position:"fixed",
	left:"0",
	right:"0",
	top:"30%",
	marginLeft:"auto",
	marginRight:"auto",
	zIndex:"9999"
};
var alertBgStyle={
	margin:"0",
    padding:"0",
    position:"fixed",
    zIndex:"9998",
    width:"100%",
	height:"100%",
	left:"0px",
	top:"0px",
	background:"#000",
	fiter:"alpha(opacity=50)",
	mozOpacity:"0.5",
	opacity:"0.5"
};
var widgetStyle={
	background: "#fafafa",
	border:"1px solid #c9c9c9",
	webkitBorderRadius:"3px",
    MozBorderRadius:"3px",
    borderRadius:"3px",
	marginBottom: "20px",
	marginTop: "10px",
	minHeight: "100px"
};
var widgetHeadStyle={
	backgroundColor:"#f8f8f8",
	background:"-webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f2f2f2))",
	background:"-webkit-linear-gradient(top, #f8f8f8, #f2f2f2)",
	background:"-moz-linear-gradient(top, #f8f8f8, #f2f2f2)",
	background:"-ms-linear-gradient(top, #f8f8f8, #f2f2f2)",
	background:"-o-linear-gradient(top, #f8f8f8, #f2f2f2)",
	background:"linear-gradient(top, #f8f8f8, #f2f2f2)",
	textShadow:"0px 1px #fff",
	borderBottom:"1px solid #ccc",
	padding:"8.5px 15px",
	fontSize:"14px",
	color:"#555",
	boxShadow:"inset 0px 1px 1px #fff",
};
var alertTitleStyle={
	marginTop: "2px",
	marginBottom: "2px",
	display: "inline-block",
	innerHTML:"温馨提示"
};
var alertTitleCloseStyle={
	display: "inline-block",
	float: "right"
};
var alertCloseStyle={
	fontSize: "18px",
	fontWeight: "bold",
	cursor: "pointer",
	innerHTML:"×"
};
var widgetContentStyle={
	margin: "0",
	padding: "0"
};
var paddStyle={
	padding:"13px 15px",
};
var tippStyle={
	lineHeight: "22px",
	innerHTMl:""
};
var opBtnStyle={
	textAlign: "center",
	marginTop: "15px"
};
var btnPrimaryStyle={
	backgroundColor:"#428bca",
	borderColor:"#357ebd",
	color:"#fff",
	border:"1px solid transparent",
	webkitBorderRadius:"4px",
    MozBorderRadius:"4px",
	borderRadius:"4px",
	cursor:"pointer",
	display:"inline-block",
	fontSize:"12px",
	fontWeight:"normal",
	lineHeight:"1.42857",
	marginBottom:"0",
	padding:"3px 10px",
	textAlign:"center",
	verticalAlign:"middle",
	whiteSpace:"nowrap",
	innerHTML:"确定"
};
var btnDefaultStyle={
	backgroundColor:"#e6e6e6",
	borderColor:"#ccc",
	color:"#333",
	border:"1px solid transparent",
	webkitBorderRadius:"4px",
    MozBorderRadius:"4px",
	borderRadius:"4px",
	cursor:"pointer",
	display:"inline-block",
	fontSize:"12px",
	fontWeight:"normal",
	lineHeight:"1.42857",
	marginBottom:"0",
	padding:"3px 10px",
	textAlign:"center",
	verticalAlign:"middle",
	whiteSpace:"nowrap",
	marginLeft: "10px",
	innerHTML:"取消"
};
var myFunction="";
var cancelFunction="";
alertObj = {
    generalSet: generalStyle,
    alertBgSet:alertBgStyle,
    widgetSet: widgetStyle,
    widgetHeadSet: widgetHeadStyle,
    alertTitleSet:alertTitleStyle,
    alertTitleCloseSet:alertTitleCloseStyle,
    alertCloseSet:alertCloseStyle,
    widgetContentSet: widgetContentStyle,
    paddSet: paddStyle,
    tippSet: tippStyle,
    opBtnSet: opBtnStyle,
    btnPrimarySet: btnPrimaryStyle,
    isExist: false
};
confirmObj={
    generalSet: generalStyle,
    alertBgSet:alertBgStyle,
    widgetSet: widgetStyle,
    widgetHeadSet: widgetHeadStyle,
    alertTitleSet:alertTitleStyle,
    alertTitleCloseSet:alertTitleCloseStyle,
    alertCloseSet:alertCloseStyle,
    widgetContentSet: widgetContentStyle,
    paddSet: paddStyle,
    tippSet: tippStyle,
    opBtnSet: opBtnStyle,
    btnPrimarySet: btnPrimaryStyle,
    btnDefaultSet: btnDefaultStyle,
    isExist: false
};
function createComponent(){
    var component = document.createElement(arguments[0]);
    var styles = arguments[1];
    for (var property in styles) {
        if (styles[property] != null) {
            try{
                component.style[property] = styles[property];
            }catch(err){
                document.write(err.name+":"+property+"<br/>");//set property error!
            }
        }
    }
    return component;
};
alertObj.show = function(){
    if(!this.isExist){
        this.isExist = true;
        var bodyObj = document.body;
        var generalObj = createComponent("div", this.generalSet);
        generalObj.id="system-alert";
        var alertBgObj = createComponent("div", this.alertBgSet);
        alertBgObj.id="system-alert-bg";
        var widgetObj = createComponent("div", this.widgetSet);
        var widgetHeadObj=createComponent("div",this.widgetHeadSet);
        var alertTitleObj=createComponent("div",this.alertTitleSet);
        alertTitleObj.innerHTML=this.alertTitleSet.innerHTML;
        var alertTitleCloseObj=createComponent("div",this.alertTitleCloseSet);
        var alertCloseObj=createComponent("span",this.alertCloseSet);
        alertCloseObj.innerHTML=this.alertCloseSet.innerHTML;
        var widgetContentObj=createComponent("div",this.widgetContentSet);
        var paddObj=createComponent("div",this.paddSet);
        var tippObj=createComponent("p",this.tippSet);
        tippObj.innerHTML = arguments[0];
        var opBtnObj=createComponent("p",this.opBtnSet);
        var btnPrimaryObj=createComponent("button",this.btnPrimarySet);
        btnPrimaryObj.innerHTML = this.btnPrimarySet.innerHTML;
        alertCloseObj.onclick = function() {
            bodyObj.removeChild(generalObj);
            bodyObj.removeChild(alertBgObj);
            if(alertObj.isExist){
                alertObj.isExist = false;
            }
            if(myFunction!=""){
                myFunction();
            }
        };
        btnPrimaryObj.onclick = function() {
        	bodyObj.removeChild(generalObj);
        	bodyObj.removeChild(alertBgObj);
        	if(alertObj.isExist){
        		alertObj.isExist = false;
        	}
        	if(myFunction!=""){
        		myFunction();
        	}
        };
        alertTitleCloseObj.appendChild(alertCloseObj);
        opBtnObj.appendChild(btnPrimaryObj);
        widgetHeadObj.appendChild(alertTitleObj);
        widgetHeadObj.appendChild(alertTitleCloseObj);
        paddObj.appendChild(tippObj);
        paddObj.appendChild(opBtnObj);
        widgetContentObj.appendChild(paddObj);
        widgetObj.appendChild(widgetHeadObj);
        widgetObj.appendChild(widgetContentObj);
        generalObj.appendChild(widgetObj);
        bodyObj.appendChild(alertBgObj);
        bodyObj.appendChild(generalObj);
    }
};
confirmObj.show = function(){
    if(!this.isExist){
        this.isExist = true;
        var bodyObj = document.body;
        var generalObj = createComponent("div", this.generalSet);
        generalObj.id="system-alert";
        var alertBgObj = createComponent("div", this.alertBgSet);
        alertBgObj.id="system-alert-bg";
        var widgetObj = createComponent("div", this.widgetSet);
        var widgetHeadObj=createComponent("div",this.widgetHeadSet);
        var alertTitleObj=createComponent("div",this.alertTitleSet);
        alertTitleObj.innerHTML=this.alertTitleSet.innerHTML;
        var alertTitleCloseObj=createComponent("div",this.alertTitleCloseSet);
        var alertCloseObj=createComponent("span",this.alertCloseSet);
        alertCloseObj.innerHTML=this.alertCloseSet.innerHTML;
        var widgetContentObj=createComponent("div",this.widgetContentSet);
        var paddObj=createComponent("div",this.paddSet);
        var tippObj=createComponent("p",this.tippSet);
        tippObj.innerHTML = arguments[0];
        var opBtnObj=createComponent("p",this.opBtnSet);
        var btnPrimaryObj=createComponent("button",this.btnPrimarySet);
        btnPrimaryObj.innerHTML = this.btnPrimarySet.innerHTML;
        var btnDefaultObj=createComponent("button",this.btnDefaultSet);
        btnDefaultObj.innerHTML = this.btnDefaultSet.innerHTML;
        alertCloseObj.onclick = function() {
            bodyObj.removeChild(generalObj);
            bodyObj.removeChild(alertBgObj);
            if(confirmObj.isExist){
                confirmObj.isExist = false;
            }
            if(cancelFunction){
                cancelFunction();
            }
        };
        btnPrimaryObj.onclick = function() {
        	bodyObj.removeChild(generalObj);
        	bodyObj.removeChild(alertBgObj);
        	if(confirmObj.isExist){
        		confirmObj.isExist = false;
        	}
        	if(myFunction!=""){
        		myFunction();
        	}
        };
        btnDefaultObj.onclick = function() {
        	bodyObj.removeChild(generalObj);
        	bodyObj.removeChild(alertBgObj);
            if(confirmObj.isExist){
                confirmObj.isExist = false;
            }
            if(cancelFunction){
                cancelFunction();
            }
        };
        alertTitleCloseObj.appendChild(alertCloseObj);
        opBtnObj.appendChild(btnPrimaryObj);
        opBtnObj.appendChild(btnDefaultObj);
        widgetHeadObj.appendChild(alertTitleObj);
        widgetHeadObj.appendChild(alertTitleCloseObj);
        paddObj.appendChild(tippObj);
        paddObj.appendChild(opBtnObj);
        widgetContentObj.appendChild(paddObj);
        widgetObj.appendChild(widgetHeadObj);
        widgetObj.appendChild(widgetContentObj);
        generalObj.appendChild(widgetObj);
        bodyObj.appendChild(alertBgObj);
        bodyObj.appendChild(generalObj);
    }
};
function show(s,v) {
    alertObj.show(s);
    var juLi=document.getElementById("system-alert").offsetHeight;
    document.getElementById("system-alert").style.top=(document.documentElement.clientHeight-juLi)/2+"px";
    if(v){
        myFunction=v;
    }else{
        myFunction="";
    }
};
function myConfirm(s,v,k){
    confirmObj.show(s);
    var juLi=document.getElementById("system-alert").offsetHeight;
    document.getElementById("system-alert").style.top=(document.documentElement.clientHeight-juLi)/2+"px";
    if(v){
        myFunction=v;
    }else{
        myFunction="";
    }
    if(k){
        cancelFunction=k;
    }else{
        cancelFunction="";
    }
};