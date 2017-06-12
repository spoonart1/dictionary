<?php 

include 'phpQuery.php';


const BASE_URL = "http://kamusslang.com/arti/";

const TERM_CONTAINER = "term-container";
const TERM_DEF = "term-def";
const TERM_EXAMPLE = "term-xample"; 
const TERM_META = "term-meta";
const TERM_SOCIALITIES = "term-socialites";
const TERM_GRAPH = "graph-container";
const ID_TERM_LIKE_DISLIKE = "like-dislike-words";
const TERM_MOST_SEEN_WORDS = "widget-content.clearfix";
const TERM_META = "term-meta";

const KATA_LAINNYA = "kata-lainnya";
const KATA_ACAK = "kata-acak";
const KATA_TERJOROK = "kata-jorok";


$total_like = 0;
$total_dislike = 0;

$word = "none";
$curl = curl_init();

if(isset($_GET['word'])){
	$word = $_GET['word'];
}

$url = BASE_URL.$word;
curl_setopt($curl, CURLOPT_URL, $url);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

$page = curl_exec($curl);
curl_close($curl);
$doc = phpQuery::newDocument($page);

if(curl_errno($curl)){
	echo 'Scrapper Error : '.curl_error($curl);
}

$widget = getContents('div', 'widget-content');
$contents = pq($doc)->find($widget);
$content_arti = array();

$term_container = getContents('div',TERM_CONTAINER);
$term_def = getContents('p',TERM_DEF);
$term_sample = getContents('p', TERM_EXAMPLE);
$term_meta = getContents('p', TERM_META);

$data = array();
$data["data"] = array();
$key = "detail";
$counter = 0;
$dd = array();
foreach( $term = pq($contents)->find($term_container) as $inner) {
	$pq = pq($inner);
	$id = $pq->attr('id');
	$description = $pq->find($term_def)->text();
	$sample = $pq->find($term_sample)->text();
	$meta = $pq->find($term_meta);
	$strong = pq($meta)->find('strong')->text();

	$like = getLikeDislikes($pq)[1];
	$dislike = getLikeDislikes($pq)[2];

	$content_arti[$key]['id'] = $id;
	$content_arti[$key]['description'] = $description;
	$content_arti[$key]['example'] = $sample;
	$content_arti[$key]['like'] = $like;
	$content_arti[$key]['dislike'] = $dislike;
	$content_arti[$key]['meta'] = $strong;

	array_push($data["data"], $content_arti);
}

createJsonArray($data);
getMostSeenWords(KATA_ACAK);
getMostSeenWords(KATA_LAINNYA);
getMostSeenWords(KATA_TERJOROK);

function getContents($tag, $class_name){
	$result = $tag.".".$class_name;
	return $result;
}

function getLikeDislikes($pq){
	$tt = array();
	$tag_social = $pq->find(getContents('div',TERM_SOCIALITIES));
	$graph_container = $tag_social->find(getContents('div', TERM_GRAPH));
	foreach (pq($graph_container)->find('span') as $span) {
		$r = pq($span)->text();
		$tt[] = $r;
	}
	return $tt;
}                                                                           

function createJsonArray($contents){
	if(!$contents){
		$null = array();
		$null['data']['id']= null;
		$null['data']['description']= null;
		$null['data']['example']= null;
		$null['data']['like']= null;
		$null['data']['dislike']= null;
		$null['data']['meta']= null;
		echo json_encode(null);

	}else{
		$json = json_encode($contents);
		echo $json;
	}
}

function getMostSeenWords($div_id){
	$most_seen_widget = getContents('div', TERM_MOST_SEEN_WORDS);
	$most_tag = $div_id;
	foreach($widget = $most_visited_words = pq($doc)->find('div.widget.blue') as $blue){
		$pq = pq($blue);
		$id = $pq->attr('id');
		$list = array();
		if($id == $most_tag){
			$inner_div = $pq->find($most_seen_widget);
			foreach($words = $inner_div->find('li') as $li){
				$list[] = pq($li)->text();
			}
			return;
		}
	}
}

?>