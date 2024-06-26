<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>

  <body>
    <!-- Timeline Style -->
    <link rel="stylesheet" href="${path}/css/timeline/timeline.css">
    <link rel="stylesheet" href="${path}/css/template/timeline/prettify.css">
    <!-- Demo style -->
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${path}/css/template/timeline/demo.css">

    <header class="header-wrapper">
      <div class="container">
        <div class="page-intro">
          <h1>Timeline.js</h1>
          <h2>easily creates timeline slider.</h2>
          <div class="page-into-buttons">
            <a class="github-button" href="https://github.com/ilkeryilmaz/timelinejs" data-style="mega" data-count-href="/ilkeryilmaz/timelinejs/stargazers" data-count-api="/repos/ilkeryilmaz/timelinejs#stargazers_count" data-count-aria-label="# stargazers on GitHub" aria-label="Star ilkeryilmaz/timelinejs on GitHub">Star</a>
          </div>
        </div>
      </div>
    </header><!-- /.header-wrapper -->

    <main class="main-wrapper container">
      <section class="section-list">
        <div class="container">
          <h2>Getting Started</h2>
					<h3>1.1. Installation with Package Managers</h3>
					<div>
						<p>Timeline.js is now setup and ready to be used with <a href="https://bower.io/" target="_blank">Bower</a> and <a href="https://www.npmjs.com/package/timelinejs-slider" target="_blank">NPM</a> and can be installed using the following commands.
						</p>
						<pre class="prettyprint lang-shell">
<code>bower install timelinejs-slider</code>
</pre>
						<pre class="prettyprint lang-shell">
<code>npm install timelinejs-slider</code>
</pre>
					</div>

					<h3>1.2. The Basics</h3>

          <div>
            <p>Include the jQuery library and plugin:</p>
<pre class="prettyprint lang-javascript">
&lt;script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"&gt;&lt;/script&gt;
&lt;script type="text/javascript" src="dist/js/timeline.min.js"&gt;&lt;/script&gt;
</pre>
          </div>

          <div>
            <p>Include the plugin css file:</p>
<pre class="prettyprint lang-html">
&lt;link rel="stylesheet" href="dist/css/timeline.min.css" /&gt;
</pre>

          </div>

          <div>
            <p>Html markup:</p>
<pre class="prettyprint lang-html">
&ltdiv class="timeline-container timeline-theme-1"&gt;
  &ltdiv class="timeline js-timeline"&gt;
    &ltdiv data-time="2017"&gt;
      your content or markup
    &lt/div&gt;
    &ltdiv data-time="2016"&gt;
      your content or markup
    &lt/div&gt;
    &ltdiv data-time="2015"&gt;
      your content or markup
    &lt/div&gt;
    &ltdiv data-time="2014"&gt;
      your content or markup
    &lt/div&gt;
    &ltdiv data-time="2013"&gt;
      your content or markup
    &lt/div&gt;
  &lt/div&gt;
&lt/div&gt;
</pre>
          </div>

          <div>
            <p>Start Plugin:</p>
<pre class="prettyprint lang-js">
$('.js-timeline').Timeline();
</pre>
          </div>

        </div>
      </section>
      <section class="section-list">
        <div class="container">
          <h2>Examples</h2>
          <div class="section-list-box">
            <h3>Default</h3>
            <pre class="prettyprint lang-js">
$('.timeline-1').Timeline();
</pre>
            <div class="timeline-container">
              <div class="timeline timeline-1">
                <div data-time="1881">
                  <div class="timeline-visual">
                    <img src="${path}/img/template/timeline/ataturk/ataturk-4.jpg" alt="">
                  </div>
                  <div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Birth of Mustafa Kemal Ataturk to Ali Riza and ZÃ¼beyde at Salonika.</li>
										</ul>
                  </div>
                </div>
								<div data-time="1883">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>German military mission established in Ottoman Empire.</li>
										</ul>
									</div>
								</div>
								<div data-time="1893">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Young Mustafa enters Military Secondary School at Salonika and is given the additional name of Kemal.</li>
										</ul>
									</div>
								</div>
								<div data-time="1895">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-11.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Mustafa Kemal enters Military Training School at Monastir.</li>
										</ul>
									</div>
								</div>
								<div data-time="1896">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-5.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Revolt by students of Military Medical School in Istanbul suppressed.</li>
										</ul>
									</div>
								</div>
								<div data-time="1897">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-6.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>17 April. Ottoman-Greek war commences, following a Greek threat to annex Crete. Peace settlement by intervention of the European Powers.</li>
										</ul>
									</div>
								</div>
								<div data-time="1898">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-10.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>State visit of Kaiser Wilhelm II to Ottoman Empire.</li>
										</ul>
									</div>
								</div>
								<div data-time="1899">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-5.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>13 March. Mustafa Kemal enters War College in Istanbul.</li>
										</ul>
									</div>
								</div>
								<div data-time="1902">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-10.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Mustafa Kemal graduates as Lieutenant, General Staff College in Istanbul.</li>
										</ul>
									</div>
								</div>
								<div data-time="1905">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-11.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>11 January. Mustafa Kemal graduates from the General Staff College with the rank of Staff Captain and is posted to the Fifth Army, based in Damascus</li>
										</ul>
									</div>
								</div>
								<div data-time="1906">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>October. Mustafa Kemal helps to found Fatherland (Vatan) Society in Damascus.</li>
										</ul>
									</div>
								</div>
								<div data-time="1907">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-9.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>20 June. Mustafa Kemal promoted Adjutant-Major.</li>
											<li>September. Mustafa Kemal posted to Third Army at Salonika.</li>
										</ul>
									</div>
								</div>
								<div data-time="1908">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-8.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>24 July. 'Young Turk' Revolution in Salonika. Committee of Union and Progress forces Abdulhamit to restore Constitution of 1876 and recall Parliament.</li>
											<li>5 October. Bulgaria proclaims independence.</li>
											<li>7 October. Austria-Hungary annexes Bosnia and Herzegovina.</li>
											<li>12 October. Crete votes for Union with Greece.</li>
											<li>Mustafa Kemal sent to Tripolitania on mission for Committee of Union and Progress.</li>
										</ul>
									</div>
								</div>
								<div data-time="1909">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-6.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>13 April. Counter-revolution in Istanbul. Union and Progress striking force, with Mustafa Kemal as divisional chief of staff, marches on the city from Salonika.</li>
											<li>27 April.  Deposition  and exile  of Abdulhamit.  Succession of Mehmet V as Sultan.</li>
											<li>Mustafa Kemal attends Congress of Union and Progress Party in Salonika.</li>
											<li>6 September. Mustafa Kemal appointed Commander of Third Army and later Commander of Thirty-eighth Infantry Regiment.</li>
										</ul>
									</div>
								</div>
								<div data-time="1910">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>Mustafa Kemal serves as Chief of Staff in suppression of revolt in Albania.</li>
											<li>Mustafa Kemal sent to Paris with military mission  to attend  French army manoeuvres.</li>
										</ul>
									</div>
								</div>
								<div data-time="1911">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>13 September. Mustafa Kemal is posted to the General Staff in Istanbul.</li>
											<li>5 October. Italian invasion of Tripoli.</li>
											<li>Mustafa Kemal with Turkish Forces at Tobruk and Derna.</li>
											<li>27 November. Mustafa Kemal promoted to Major.</li>
										</ul>
									</div>
								</div>
								<div data-time="1912">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-8.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>8 October - 3 December. Beginning of First Balkan War. Montenegro, Serbia, Bulgaria and Greece at war with Ottoman Empire. Severe Turkish defeats. Salonika falls to the Greeks. Mustafa Kemal leaves Cyrenaica and returns to Istanbul. Coup d'etat against Government by Union and Progress officers.</li>
											<li>25 November. Mustafa Kemal appointed Director of Operations for relief of Adrianople.</li>
											<li>Fall of Adrianople.</li>
										</ul>
									</div>
								</div>
								<div data-time="1913">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-5.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>30 May. Treaty of London between Ottomans and Balkan states.</li>
											<li>30 June - 20 July. Second Balkan War. Bulgaria attacks Greece, Serbia and Rumania. Ottomans recovers Adrianople.</li>
											<li>27 September. Treaty of Bucharest restores territory to Ottoman Empire.</li>
											<li>27 October. Mustafa Kemal appointed Military AttachÃ© in Sofia.</li>
										</ul>
									</div>
								</div>
								<div data-time="1914">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>1 March. Mustafa Kemal promoted Lieutenant-Colonel.</li>
											<li>28 June. Assassination of Archduke Franz Ferdinand at Sarajevo.</li>
											<li>16 July. Mustafa Kemal sends despatch to War Minister from Sofia, urging a policy of Turkish neutrality in the event of war, with a view to possible later intervention against Bulgaria and the Central Powers.</li>
											<li>28 July. Austria declares war on Serbia, with support of Germany.</li>
											<li>2 August. Ottoman leadership signs secret alliance with Germany.</li>
											<li>11 August. Ottomans purchase German warships Goeben and Breslau on arrival in the Bosporus.</li>
											<li>28 October. Ottomans shells Russian Black Sea ports.</li>
											<li>3 November. Russia declares war on Ottoman Empire.</li>
											<li>5 November. Britain and France declare war on Ottoman Empire.</li>
										</ul>
									</div>
								</div>
								<div data-time="1915">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>2 February. Mustafa Kemal appointed to reorganize and command Nineteenth Division in Thrace.</li>
											<li>19 February. Unsuccessful Allied naval attack on Dardanelles.</li>
											<li>25 February. Mustafa Kemal establishes Headquarters of Nineteenth Division at Maidos on Gallipoli Peninsula.</li>
											<li>18 March. Allied navy fails to force the Turkish Straits. Mustafa Kemal demonstrates outstanding skills as a soldier and commander throughout the battle at Canakkale.</li>
											<li>25 April. Allied military landings at Ariburnu (Anzac Cove).  Advance checked by Mustafa Kemal with his outstanding leadership of the Nineteenth Division.</li>
											<li>1 June. Mustafa Kemal promoted Colonel.</li>
											<li>8 - 9 August. Mustafa Kemal appointed to command of Sixteenth Army Corps.</li>
											<li>Checks second Allied advance on Gallipoli Peninsula.</li>
										</ul>
									</div>
								</div>
								<div data-time="1916">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-4.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>9 January. Allied evacuation of Gallipoli Peninsula complete.</li>
											<li>14 January. Mustafa Kemal posted to Adrianople in command of Sixteenth Army Corps. Transfer to Caucasus front.</li>
											<li>1 April. Mustafa Kemal promoted General and Pasha.</li>
											<li>27 June. Sherif of Mecca proclaims independence of Arabia.</li>
											<li>6 - 7 August. Mustafa Kemal recaptures Bitlis and MuÅ from Russians.</li>
										</ul>
									</div>
								</div>
								<div data-time="1917">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-5.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>5 March.  Mustafa Kemal appointed second-in-command effective Commander of Second Army</li>
											<li>11 March. British forces capture Baghdad.</li>
											<li>5 July. Mustafa Kemal appointed Commander of Seventh Army in Syria.</li>
											<li>20 September. Mustafa Kemal sends report to Government on the poor state of the army and the country, and relinquishes his command.</li>
											<li>October. Mustafa Kemal returns to Istanbul.</li>
											<li>11 December. British forces capture Jerusalem.</li>
											<li>15 December - 5 January 1918. Mustafa Kemal visits Germany with Crown Prince Vahdettin.</li>
										</ul>
									</div>
								</div>
								<div data-time="1918">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-7.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>3 July. Death of Sultan Mehmet V. Vahdettin succeeds him as Mehmet VI.</li>
											<li>7 August.  Mustafa Kemal reappointed Commander of Seventh Army  in Palestine.</li>
											<li>19 - 30 September.  British forces,  under  General  Allenby,  drive Turkish forces out of Palestine and Syria. Mustafa Kemal defends frontier north of Aleppo.</li>
											<li>30 October. Armistice signed between Ottomans and Britain at Mudros.</li>
											<li>31 October. Mustafa Kemal takes over command of Army Group at Adana.</li>
											<li>7 November. Dissolution of Army Group.</li>
											<li>13 November. Mustafa Kemal returns to Istanbul.</li>
											<li>Allied fleets enter Istanbul.</li>
											<li>21 November. Dissolution of Parliament.</li>
										</ul>
									</div>
								</div>
								<div data-time="1919">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-11.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>18 January. Opening of Peace Conference at Versailles.</li>
											<li>30 April. Mustafa Kemal appointed Inspector-General of Ninth (later Third) Army in Anatolia.</li>
											<li>15 May. Greek forces land in Izmir, with Allied approval.</li>
											<li>19 May. Mustafa Kemal lands in Samsun.</li>
											<li>21 June.  Mustafa Kemal issues 'Declaration of Independence' at Amasya.</li>
											<li>Summons Nationalist Congress to be held in Sivas.</li>
											<li>23 June. Mustafa Kemal ordered by the Ottoman Government to return to Istanbul.</li>
											<li>8 July. Mustafa Kemal resigns from the army and is dismissed by the Ottoman Government.</li>
											<li>23 July - 7 August. Nationalist Congress meets in Erzurum under presidency of Mustafa Kemal. Declarations of National Pact are issued.</li>
											<li>4 - 13 September. Nationalist Congress meets at Sivas under presidency of Mustafa Kemal. Confirmation of National Pact. Establishment of Representative Committee.</li>
											<li>5 October. Resignation of Government.</li>
											<li>7 November. New Parliament elected in Istanbul with Nationalist representation.</li>
											<li>27 December. Mustafa Kemal establishes headquarters in Ankara, with Representative Committee.</li>
										</ul>
									</div>
								</div>
								<div data-time="1920">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-6.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>28 January. National Pact adopted by Istanbul Parliament.</li>
											<li>9 February. Evacuation of French garrison from MaraÅ and start of general withdrawal from Cilicia.</li>
											<li>16 March. Military occupation of Istanbul by Allies.</li>
											<li>11 April. Dissolution of Istanbul Parliament.</li>
											<li>23 April. First Grand National Assembly meets in Ankara.</li>
											<li>11 May. Mustafa Kemal condemned to death by Sultan's Government.</li>
											<li>10 June. Treaty of Sevres presented by Allies to Sultan's Government.</li>
											<li>22 June - 9 July. Greek army advances into Anatolia and captures Bursa.</li>
											<li>10 August. Treaty of Sevres signed by Sultan's Government.</li>
											<li>24 August. Draft treaty initialled in Moscow between Soviet Union and Nationalist Government.</li>
											<li>28 September - 2 November. Nationalist forces invade regions with Armenian revolts and capture Kars.</li>
											<li>2 December. Soviet Union establishes Armenian Republic at Erivan.</li>
											<li>3 December. Treaty of GÃ¼mrÃ¼ settles Turco-Armenian frontiers.</li>
										</ul>
									</div>
								</div>
								<div data-time="1921">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-9.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>6 - 10 January. Greek advance checked at First Battle of Inonu.</li>
											<li>20 January. Grand National Assembly at Ankara adopts Constitution Act, based on popular sovereignty.</li>
											<li>23 February - 12 March. London Conference fails to reach agreement with Nationalists on modifications to Treaty of Sevres.</li>
											<li>16 March. Treaty of Moscow between Nationalist Government and Soviet Union.</li>
											<li>23 March - 1 April. Greeks resume offensive in Anatolia and are checked at Second Battle of Inonu.</li>
											<li>10 July. Greeks resume offensive and capture EskiÅehir.</li>
											<li>5 August. Mustafa Kemal given full powers as Commander-in-Chief by Grand National Assembly.</li>
											<li>23 August - 13 September. Battle of Sakarya. Turks check Greek advance before Ankara.</li>
											<li>26 August. Greeks thrown back at battle of Sakarya.</li>
											<li>19 September. Mustafa Kemal given title of âGaziâand rank of Marshal by Grand National Assembly.</li>
											<li>13 October. Treaty of Kars between Nationalist Government and Transcaucasian Soviet Republics.</li>
											<li>20 October. Treaty of Ankara between Nationalist Government and France.</li>
										</ul>
									</div>
								</div>
								<div data-time="1922">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-7.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>26 August - 9 September. Nationalist forces defeat Greeks in counter-offensive and capture Izmir, which is destroyed by fire. Turks launch final offensive against Greek forces in Anatolia; break through the following day; win decisive victory on 30 August.</li>
											<li>23 September.  Nationalist forces enter the Turkish Straits Neutral Zone.</li>
											<li>3 - 11 October. Conference at Mudanya agrees on Armistice between Allies and Nationalist Government.</li>
											<li>19 October. Resignation of Lloyd George and his Government.</li>
											<li>1 November. Mustafa Kemal proclaims abolition of Sultanate.</li>
											<li>17 November. Flight of Sultan Mehmet VI from Istanbul.</li>
											<li>20 November. Opening of Peace Conference at Lausanne.</li>
										</ul>
									</div>
								</div>
								<div data-time="1923">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-8.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>14 January. Death of Mustafa Kemal's mother, in Izmir.</li>
											<li>29 January. Mustafa Kemal marries Latife in Izmir.</li>
											<li>4 February. Breakdown of Lausanne Conference.</li>
											<li>17 February. Mustafa Kemal opens Economic Congress in Izmir.</li>
											<li>23 April. Resumption of Lausanne Conference.</li>
											<li>24 July.  Treaty of Lausanne signed in Switzerland by Turkey and the Entente powers that fought in World War I. After the conclusion of the Turkish War of Independence, this treaty recognized the Republic of Turkey as a sovereign nation.</li>
											<li>9 August. Foundation of People's Party (Cumhuriyet Halk Partisi, CHP).</li>
											<li>11 August. Second Grand National Assembly.</li>
											<li>2 October. Turkish forces occupy Istanbul, following Allied evacuation.</li>
											<li>9 October. Ankara becomes capital of Turkey.</li>
											<li>29 October. Proclamation of the Turkish Republic, with Mustafa Kemal as President.</li>
										</ul>
									</div>
								</div>
								<div data-time="1924">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>3 March. The Caliphate is abolished, the Ottoman dynasty is exiled, religious schools are closed down, and organized Islam becomes regulated by the state.</li>
											<li>8 April. Abolition of religious courts.</li>
											<li>17 November. Foundation of Progressive Party.</li>
										</ul>
									</div>
								</div>
								<div data-time="1925">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>11 February - 12 April. Revolt in eastern region.</li>
											<li>4 March. Law for Maintenance of Public Order gives Government exceptional powers.</li>
											<li>3 June. Suppression of Progressive Party.</li>
											<li>5 August. Mustafa Kemal divorces Latife.</li>
											<li>30 August - 2 September.   Mustafa Kemal tours Kastamonu province, announcing abolition of fez, suppression of religious brotherhoods, and closing of sacred tombs as places of worship.</li>
										</ul>
									</div>
								</div>
								<div data-time="1926">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>17 February. Adoption of new Civil Law code.</li>
											<li>5 June. Agreement on Mosul. Treaty of Ankara between Turkey, Britain and Irak.</li>
											<li>15 June - 13 July. Attempted assassination of Mustafa Kemal in Izmir is uncovered.</li>
											<li>Trial and execution of ringleaders.</li>
											<li>1 - 26 August. Trial and execution of 'Young Turk' leaders and others in Ankara.</li>
										</ul>
									</div>
								</div>
								<div data-time="1927">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-9.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>1 July. Mustafa Kemal revisits Istanbul.</li>
											<li>15 - 20 October. Mustafa Kemal makes historic speech to Congress of People's Party (CHP).</li>
											<li>1 November. Third Grand National Assembly. Mustafa Kemal re-elected President of the Republic.</li>
										</ul>
									</div>
								</div>
								<div data-time="1928">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>3 November. Introduction of Latin alphabet.</li>
										</ul>
									</div>
								</div>
								<div data-time="1929">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-2.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>1 January. National schools open to teach new alphabet.</li>
											<li>4 March. Maintenance of Order Law repealed.</li>
											<li>13 May. New commercial law adopted.</li>
										</ul>
									</div>
								</div>
								<div data-time="1930">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-11.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>12 August. Foundation of Free Party.</li>
											<li>17 November. Dissolution of Free Party.</li>
											<li>23 December. Religious riot at Menemen. Trials and executions.</li>
										</ul>
									</div>
								</div>
								<div data-time="1931">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-11.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>15 April. Foundation of Turkish Historical Society.</li>
											<li>4 May. Fourth Grand National Assembly. Mustafa Kemal re-elected President of the Republic.</li>
										</ul>
									</div>
								</div>
								<div data-time="1932">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-5.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>12 July. Foundation of Turkish Linguistic Society.</li>
											<li>12 August. Turkey becomes member of League of Nations.</li>
										</ul>
									</div>
								</div>
								<div data-time="1933">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-6.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>1 February. Disturbances in Bursa over decision to recite the call to prayer in Turkish.</li>
											<li>31 May. Istanbul University is established.</li>
											<li>29 October. Celebration of the 10th Anniversary of the Turkish Republic.</li>
										</ul>
									</div>
								</div>
								<div data-time="1934">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-7.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>9 January. First Five-Year Plan for industrial development.</li>
											<li>9 February. Balkan Pact concluded between Turkey, Greece, Rumania and Yugoslavia.</li>
											<li>24 November. Mustafa Kemal adopts the surname "ATATÃRK" which is given to him by the Turkish Parliament.</li>
											<li>8 December. Women made eligible to vote in Parliamentary elections and to become members of Parliament.</li>
										</ul>
									</div>
								</div>
								<div data-time="1935">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-9.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>1 March. Fifth Grand National Assembly. Ataturk re-elected President of the Republic.</li>
										</ul>
									</div>
								</div>
								<div data-time="1936">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-3.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>29 May. Dispute regarding future status of Hatay (Alexandretta) referred to the League of Nations.</li>
											<li>20 July.  Montreux  Convention  signed,  regulating the Turkish Straits (Bosphorus and Dardanelles).</li>
											<li>4 September. Visit of King Edward VIII to Ataturk in Istanbul.</li>
										</ul>
									</div>
								</div>
								<div data-time="1937">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-10.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>27 January. Autonomy of Hatay agreed between Turkey, France and Syria.</li>
											<li>9 July.  Saadabad Pact signed, between Turkey, Irak, Iran and Afghanistan.</li>
										</ul>
									</div>
								</div>
								<div data-time="1938">
									<div class="timeline-visual">
										<img src="${path}/img/template/timeline/ataturk/ataturk-1938.jpg" alt="">
									</div>
									<div class="timeline-detail">
										<ul class="timeline-detail-list">
											<li>11 March. Illness of Ataturk is officially announced.</li>
											<li>3 July. Franco-Turkish agreement to send French and Turkish troops into Hatay, to supervise elections.</li>
											<li>2 September. Grand National Assembly votes nominal Republic of Hatay, with Turks in effective control.</li>
											<li>18 September. Second Five Year Plan for industrial development.</li>
											<li>10 November. The death of Mustafa Kemal âAtaturk,â the leader of the Turkish War of Independence and founder of modern-day Turkey.</li>
											<li>11 November. Succession of Ismet Inonu as President of Turkey.</li>
										</ul>
									</div>
								</div>

              </div>
            </div><!-- /.timeline-container -->
          </div><!-- /.section-list-box -->


          <div class="section-list-box">
            <h3>Dots Position and Custom Item Class Example:</h3>
<pre class="prettyprint lang-js">
$('.timeline-2').Timeline({
  itemClass: 'box-item',
  dotsPosition: 'top',
  startItem: 'last'
});
</pre>
            <div class="timeline-container timeline-theme-2">
              <div class="timeline timeline-small-box timeline-2">
                <div class="theme-1" data-time="1">1</div>
                <div class="theme-2" data-time="2">2</div>
                <div class="theme-3" data-time="3">3</div>
                <div class="theme-4" data-time="4">4</div>
                <div class="theme-5" data-time="5">5</div>
                <div class="theme-1" data-time="6">6</div>
                <div class="theme-3" data-time="7">7</div>
                <div class="theme-4" data-time="8">8</div>
                <div class="theme-6" data-time="9">9</div>
                <div class="theme-5" data-time="10">10</div>
              </div>
            </div><!-- /.timeline-container -->
          </div><!-- /.section-list-box -->


					<div class="section-list-box">
						<h3>Mode Vertical and Autoplay Example:</h3>
						<pre class="prettyprint lang-js">
$('.timeline-2').Timeline({
  autoplay: true,
  mode: 'vertical',
  itemClass: 'box-item',
});
</pre>
						<div class="timeline-container timeline-theme-2">
							<div class="timeline timeline-small-box timeline-3">
								<div class="theme-2" data-time="2008">1</div>
								<div class="theme-6" data-time="2009">2</div>
								<div class="theme-4" data-time="2010">3</div>
								<div class="theme-1" data-time="2011">4</div>
								<div class="theme-5" data-time="2012">5</div>
								<div class="theme-1" data-time="2013">6</div>
								<div class="theme-3" data-time="2014">7</div>
								<div class="theme-4" data-time="2015">8</div>
								<div class="theme-6" data-time="2016">9</div>
								<div class="theme-3" data-time="2017">10</div>
							</div>
						</div><!-- /.timeline-container -->
					</div><!-- /.section-list-box -->

        </div>
      </section>

      <section class="section-list">
        <div class="container">
          <h2>Options</h2>
          <p>Available options listed below.</p>
          <table class="table">
            <thead>
              <tr>
                <th>Name</th>
								<th>Default</th>
								<th>Type</th>
                <th class="row-large">Info</th>
              </tr>
            </thead>
            <tbody>
							<tr>
								<td>autoplay</td>
								<td><code>false</code></td>
								<td>
									<code>true</code>
									<code>false</code>
								</td>
								<td>Enables Autoplay.</td>
							</tr>
							<tr>
								<td>autoplaySpeed</td>
								<td><code>3000</code></td>
								<td>
									<code>int(ms)</code>
								</td>
								<td>Autoplay Speed in milliseconds</td>
							</tr>
							<tr>
								<td>mode</td>
								<td><code>'horizontal'</code></td>
								<td>
									<code>'horizontal'</code>
									<code>'vertical'</code>
								</td>
								<td>Determines the structure of the slider.</td>
							</tr>
              <tr>
                <td>itemClass</td>
                <td><code>'timeline-item'</code></td>
                <td>
                  <code>'class-name'</code>
                </td>
                <td>Timeline item class value. Makes it easy to customize.</td>
              </tr>
              <tr>
                <td>dotsClass</td>
                <td><code>'timeline-dots'</code></td>
                <td>
                  <code>'class-name'</code>
                </td>
                <td>The container class value for timeline dates.</td>
              </tr>
              <tr>
                <td>startItem</td>
                <td><code>'first'</code></td>
                <td>
                  <code>'first'</code>
                  <code>'last'</code>
                  <code>'number'</code>
                </td>
                <td>It determines which content will be active at startup. Number starts at <b>0</b>.</td>
              </tr>
              <tr>
                <td>dotsPosition</td>
                <td><code>'bottom'</code></td>
                <td>
                  <code>'bottom'</code>
                  <code>'top'</code>
                </td>
                <td>Sets the location of the timeline dates.</td>
              </tr>
              <tr>
                <td>activeClass</td>
                <td><code>'slide-active'</code></td>
                <td>
                  <code>'class-name'</code>
                </td>
                <td>Timeline items and dates active class</td>
              </tr>
              <tr>
                <td>prevClass</td>
                <td><code>'slide-prev'</code></td>
                <td>
                  <code>'class-name'</code>
                </td>
                <td>Timeline items and dates prev class</td>
              </tr>
              <tr>
                <td>nextClass</td>
                <td><code>'slide-next'</code></td>
                <td>
                  <code>'class-name'</code>
                </td>
                <td>Timeline items and dates next class</td>
              </tr>
							<tr>
								<td>pauseOnHover</td>
								<td><code>true</code></td>
								<td>
									<code>true</code>
									<code>false</code>
								</td>
								<td>Pause Autoplay On Hover</td>
							<tr>
								<td>pauseOnDotsHover</td>
								<td><code>false</code></td>
								<td>
									<code>true</code>
									<code>false</code>
								</td>
								<td>Pause Autoplay On Hover</td>
							</tr>

            </tbody>
          </table>
        </div>
      </section>
    </main><!-- /.main-wrapper -->

    <footer class="footer-wrapper">
      <div class="container">
        <div>ilker YÄ±lmaz</div>
        <a href="https://github.com/ilkeryilmaz/timelinejs" target="_blank" class="btn">Download from Github</a>
      </div>
    </footer><!-- /.footer-wrapper -->




		<script src="${path}/js/jquery/jquery-3.7.1.js"></script>

		<!-- Demo Page -->
		<script async defer src="https://buttons.github.io/buttons.js"></script>
		<script src="${path}/js/template/timeline/prettify.js"></script>
		<script src="${path}/js/template/timeline/demo.js"></script>

		<!-- Timeline -->
		<script src="${path}/js/timeline/timeline.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				// Demo 1
				$('.timeline-1').Timeline();


				// Demo 2
				$('.timeline-2').Timeline({
					itemClass: 'box-item',
					dotsPosition: 'top',
					startItem: 'last'
				});

				// Demo 3
				$('.timeline-3').Timeline({
					autoplay: true,
					mode: 'vertical',
					itemClass: 'box-item'
				});
			});
		</script>
  </body>
</html>
