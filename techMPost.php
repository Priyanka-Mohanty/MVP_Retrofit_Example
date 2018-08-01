<?php
//$jsonurl = "http://api.wipmania.com/json";
//$jsonurl = "http://www.mocky.io/v2/5b615a19300000ef0d6a4087";
//$jsonurl = "http://www.mocky.io/v2/5b618020300000d8046a416e";
$jsonurl = "http://www.mocky.io/v2/5b618026300000d8046a4170";
$json = file_get_contents($jsonurl);
//echo $json;
$jsonValue=json_decode($json);
echo json_encode($jsonValue);
?>