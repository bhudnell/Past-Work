<?php
require_once './DataBaseAdaptor.php';

if (isset ( $_POST ['mode'] )) {
	if ($_POST ['mode'] === 'add') {
		$quote = $_POST ['quote'];
		$author = $_POST ['author'];

		$myDatabaseFunctions->add($quote, $author);
	}
	elseif ($_POST ['mode'] === 'upvote') {
		$id = $_POST['id'];
		$myDatabaseFunctions->incrementRating($id);
	}
	elseif ($_POST ['mode'] === 'downvote') {
		$id = $_POST['id'];
		$myDatabaseFunctions->decrementRating($id);
	}
}

header("Location: index.php");
?>
