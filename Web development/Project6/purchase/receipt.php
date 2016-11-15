<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Purchase Receipt</title>
<link href="style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div><br>
	<h2>Warm Fish Salad Receipt</h2><br>
<?php
$first = $_POST ['firstName'];
$last = $_POST ['lastName'];
$phone = $_POST ['phone'];
$city = $_POST ['city'];
$state = $_POST ['state'];
$zip = $_POST ['zip'];
$size = $_POST ['size'];
$quantity = $_POST ['quantity'];
$smallCost = $_POST ['smallCost'];
$mediumCost = $_POST ['mediumCost'];
$largeCost = $_POST ['largeCost'];

if ($size === "small")
	$cost = $smallCost;
else if ($size === "medium")
	$cost = $mediumCost;
$cost = $largeCost;

$totalCost = $quantity * $cost;

date_default_timezone_set ( 'America/Phoenix' );
$mydate = date ( "d-M-Y" );

echo "Purchase date: " . $mydate . "<br><br>";
echo "Purchased " . $quantity . " " . $size . " salad(s) at $" . number_format($cost,2) . " each<br><br>";
echo "Total Cost: $" . number_format( $totalCost, 2 );
?>
<br><br>
<fieldset>
	<legend>Ship to</legend>
	<?php 
	echo $first . " " . $last . "<br>";
	echo $city . ", " . $state . "<br>";
	echo $zip;
	?>
</fieldset><br>

</div>
</body>
</html>