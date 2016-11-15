<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Graderator</title>
</head>
<body>
<?php
$testStr = $_POST ['testGrades'];
$projStr = $_POST ['projectGrades'];
$testWeight = $_POST ['testWeight'];
$projWeight = $_POST ['projectWeight'];

$testArr = explode ( ",", $testStr );
$projArr = explode ( ",", $projStr );

$testAvg = 0;
$projAvg = 0;

foreach ( $testArr as $score ) {
	$testAvg += ( float ) $score;
}
$testAvg = $testAvg / count ( $testArr );

foreach ( $projArr as $score ) {
	$projAvg += ( float ) $score;
}
$projAvg = $projAvg / count ( $projArr );

echo "Tests are 40% of " . round ( $testAvg, 1 ) . ", which is " . round ( $testWeight * $testAvg, 1 ) . "<br/>";
echo "Projects are 60% of " . round ( $projAvg, 1 ) . ", which is " . round ( $projWeight * $projAvg, 1 ) . "<br/><br/>";
echo "Course grade: " . round ( $testWeight * $testAvg + $projWeight * $projAvg, 1 );
?>
</body>
</html>