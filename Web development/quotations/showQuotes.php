<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Quotations</title>
<link href="styles.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<h2>Quotes</h2>
	<form id='addQuote' action='./addQuote.html' method='post'>
		<input type='submit' value='Add Quote'>
	</form><br>
<?php
require_once './DataBaseAdaptor.php';
$arrayOfQuotes = $myDatabaseFunctions->getQuotesArray ();
foreach ( $arrayOfQuotes as $record ) {
	echo "<div class='container'><div class='onequote'>";
	echo "<p class='quote'><q>";
	echo $record['quote'];
	echo "</q></p><p class='author'>";
	echo "- " . $record['author'];
	echo "</p></div><div class='rating'>";
	echo "<form action='./controller.php' method='post'>
			<input id='button' type='submit' value='+'>
			<input type='hidden' name='mode' value='upvote'>
			<input type='hidden' name='id' value=" . $record['id'] . ">
		  </form>";
	echo "<form action='./controller.php' method='post'>
			<input id='button' type='submit' value='-'>
			<input type='hidden' name='mode' value='downvote'>
			<input type='hidden' name='id' value=" . $record['id'] . ">
		  </form>";
	echo $record['rating'];
	echo "</div></div><br>";
}
?>
</body>
</html>