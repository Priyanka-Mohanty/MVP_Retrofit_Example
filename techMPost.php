<?php
//$jsonurl = "http://api.wipmania.com/json";
//$jsonurl = "http://www.mocky.io/v2/5b615a19300000ef0d6a4087";
//$jsonurl = "http://www.mocky.io/v2/5b61a85e300000221e6a4281";
$jsonurl = "http://www.mocky.io/v2/5b61a5d7300000221e6a4274";
//$jsonurl = "http://www.mocky.io/v2/5b62a5033000005200650045";
$json = file_get_contents($jsonurl);
//echo $json;
$jsonValue=json_decode($json);
echo json_encode($jsonValue);
?>