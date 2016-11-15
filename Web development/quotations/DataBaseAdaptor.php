<?php
class DatabaseAdaptor {
	private $DB;

	public function __construct() {
		$db = 'mysql:dbname=quotes;host=127.0.0.1';
		$user = 'root';
		$password = '';
			
		try {
			$this->DB = new PDO($db, $user, $password);
			$this->DB->setAttribute (PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		} catch (PDOException $e) {
			echo ('Error establishing connection');
			exit();
		}
	}

	public function getQuotesArray() {
		$stmt = $this->DB->prepare("SELECT * FROM quotations ORDER BY rating DESC, added DESC");
		$stmt->execute();
		return  $stmt->fetchAll(PDO::FETCH_ASSOC);
	}
	
	public function add($quote, $author){
		$stmt = $this->DB->prepare("INSERT INTO quotations(id, added, quote, author, rating)
				values(id, now(), :quote, :author, 0)");
		$stmt->bindParam('quote', $quote);
		$stmt->bindParam('author', $author);
		$stmt->execute();
	}
		
	public function incrementRating($id){
		$stmt = $this->DB->prepare("UPDATE quotations SET rating=rating+1 WHERE id=:id");
		$stmt->bindParam('id', $id);
		$stmt->execute();
	}
	
	public function decrementRating($id){
		$stmt = $this->DB->prepare("UPDATE quotations SET rating=rating-1 WHERE id=:id");
		$stmt->bindParam('id', $id);
		$stmt->execute();
	}
}
$myDatabaseFunctions = new DatabaseAdaptor();
?>