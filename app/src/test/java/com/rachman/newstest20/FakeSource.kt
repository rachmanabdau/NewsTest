package com.rachman.newstest20

import com.rachman.newstest.model.News
import com.rachman.newstest20.source.network.NetworkService
import com.rachman.newstest20.source.network.moshi
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeSource : NetworkService {

    override fun getTopHeadLinesAsync(
        page: Int,
        apiKey: String,
        country: String,
        category: String
    ): Deferred<Response<News>> {
        val realApiKey = BuildConfig.API_KEY
        val jsonConverter = moshi.adapter(News::class.java)
        val responseSuccess = jsonConverter.fromJson(sampleData) as News

        return if (apiKey == realApiKey) {
            // Response Success
            CompletableDeferred(Response.success(responseSuccess))

        } else {
            // Response Error
            CompletableDeferred(
                Response.error(
                    401,
                    errorResponse
                        .toResponseBody("application/json;charset=utf-8".toMediaType())
                )
            )
        }
    }


    val errorResponse = """{
    "status": "error",
    "code": "apiKeyInvalid",
    "message": "Unauthorized. Your API key was missing from the request, or wasn't correct."
}"""

    val sampleData = """{
    "status": "ok",
    "totalResults": 38,
    "articles": [
        {
            "source": {
                "id": null,
                "name": "The Guardian"
            },
            "author": "Helen Sullivan, Molly Blackall, Rebecca Smithers",
            "title": "Coronavirus live news: Biden vows to get vaccinated in public as South Korea reports highest cases in nine months - The Guardian",
            "description": "Biden will ask Americans to wear masks for his first 100 days in office; Italy approves Christmas restrictions; Iran’s cases top 1m",
            "url": "https://www.theguardian.com/world/live/2020/dec/04/coronavirus-live-news-biden-to-ask-americans-to-wear-masks-for-100-days-as-global-deaths-pass-15m",
            "urlToImage": "https://i.guim.co.uk/img/media/c090cd83408471d34aa1737ecbe2a87179cb7948/0_0_5000_3000/master/5000.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=a4e3b5245d64a10283f9ff27a7b782b2",
            "publishedAt": "2020-12-04T02:30:00Z",
            "content": null
        },
        {
            "source": {
                "id": "the-washington-post",
                "name": "The Washington Post"
            },
            "author": "Amy Gardner",
            "title": "David Perdue appears to tacitly acknowledge Biden’s victory in video call with Republican group - The Washington Post",
            "description": "Speaking just days before President Trump is set to campaign for him and fellow senator Kelly Loeffler, Perdue went further in nodding to President-elect Joe Biden’s win than he has publicly.",
            "url": "https://www.washingtonpost.com/politics/perdue-recording-biden-win/2020/12/03/8bd3ef64-35a7-11eb-8d38-6aea1adb3839_story.html",
            "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/5TFZGSBVWII6XNM4VW3RKPIQYI.jpg&w=1440",
            "publishedAt": "2020-12-04T02:07:58Z",
            "content": "But in a video meeting recorded Wednesday with members of the Republican Jewish Coalition (RJC), Perdue spoke pragmatically about the role a GOP-controlled Senate could play as a check on the Biden a… [+5736 chars]"
        },
        {
            "source": {
                "id": "the-verge",
                "name": "The Verge"
            },
            "author": "Jay Peters",
            "title": "You can play Cyberpunk 2077 on December 9th instead of 10th, depending where you live - The Verge",
            "description": "The highly-anticipated Cyberpunk 2077 is just days away from its December 10th release. But depending on where you live and what platform you’re playing on, you might get to play the game a bit earlier than you might have expected.",
            "url": "https://www.theverge.com/2020/12/3/22151420/cyberpunk-2077-december-9th-10th-release-date-unlock-timing-launch",
            "urlToImage": "https://cdn.vox-cdn.com/thumbor/NKHlBzaUiDqSUzvh5lPH_6B7J6I=/0x31:1600x869/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/22142734/screen_image_about_768457e5.jpg",
            "publishedAt": "2020-12-04T01:53:39Z",
            "content": "Cyberpunk 2077 is almost here\r\nImage: CD Projekt Red\r\nThe highly-anticipated Cyberpunk 2077 is just days away from its December 10th release. But depending on where you live and what platform youre p… [+1819 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "ESPN"
            },
            "author": "Dave McMenamin",
            "title": "Jared Dudley thinks Los Angeles Lakers teammate Anthony Davis primed for MVP season - ESPN",
            "description": "Jared Dudley sees an MVP award in the future for Lakers teammate Anthony Davis. \"He has the talent, he has the determination. ... He's the future and he's the now.\"",
            "url": "https://www.espn.com/nba/story/_/id/30446163/jared-dudley-thinks-los-angeles-lakers-teammate-anthony-davis-primed-mvp-season",
            "urlToImage": "https://a.espncdn.com/combiner/i?img=%2Fphoto%2F2020%2F1204%2Fr785499_1296x729_16%2D9.jpg",
            "publishedAt": "2020-12-04T01:45:29Z",
            "content": "LOS ANGELES -- Anthony Davis might have filled his bank account by signing a five-year, ${'$'}190 million extension with the Los Angeles Lakers on Thursday, but one of his teammates now wants him to fill … [+2565 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "KVIA El Paso"
            },
            "author": "Jim Parker",
            "title": "El Paso leaders cautiously optimistic about virus spread but health dept. won't shorten quarantine time - KVIA El Paso",
            "description": "EL PASO, Texas -- El Paso Mayor Dee Margo and County Judge Ricardo Samniego expressed cautious optimism amid declining virus cases and hospitalizations during a Thursday afternoon briefing at City Hall on the Covid-19 situation locally. However, the two men a…",
            "url": "https://kvia.com/coronavirus/2020/12/03/watch-live-at-3pm-el-paso-mayor-officials-hold-covid-19-briefing/",
            "urlToImage": "https://kvia.b-cdn.net/2020/02/Screen-Shot-2020-02-27-at-6.34.58-PM-860x484.png",
            "publishedAt": "2020-12-04T00:56:15Z",
            "content": "EL PASO, Texas -- El Paso Mayor Dee Margo and County Judge Ricardo Samniego expressed cautious optimism amid declining virus cases and hospitalizations during a Thursday afternoon briefing at City Ha… [+2573 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "The Guardian"
            },
            "author": "Peter Beaumont",
            "title": "Trump kick-starts oil drilling licence sales in Arctic refuge - The Guardian",
            "description": "Fossil fuel extraction sell-off in pristine Alaskan wilderness set for 6 January, predating Biden inauguration by days",
            "url": "https://amp.theguardian.com/world/2020/dec/03/trump-kick-starts-oil-drilling-licence-sales-in-arctic-refuge",
            "urlToImage": null,
            "publishedAt": "2020-12-04T00:56:00Z",
            "content": "This land is your landFossil fuel extraction sell-off in pristine Alaskan wilderness set for 6 January, predating Biden inauguration by days\r\nThe Trump administration has formally announced the go-ah… [+3938 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Eonline.com"
            },
            "author": "Lindsay Weinberg",
            "title": "Noah Cyrus Is ''Mortified'' After Using Racially Insensitive Word While Defending Harry Styles - E! NEWS",
            "description": "Noah Cyrus feels \"horrified\" after she used a racially insensitive word when supporting Harry Styles' fashion choices. Read the 20-year-old singer's apology below.",
            "url": "https://www.eonline.com/news/1215050/noah-cyrus-is-mortified-after-using-racially-insensitive-word-while-defending-harry-styles",
            "urlToImage": "https://akns-images.eonline.com/eol_images/Entire_Site/2020922/rs_1200x1200-201022081819-1200-Noah-Cyrus-CMT.jpg?fit=around%7C1080:1080&output-quality=90&crop=1080:1080;center,top",
            "publishedAt": "2020-12-04T00:46:00Z",
            "content": "Earlier this week, Harry defended himself against Candace's critiques of his Vogue cover. The \"Adore You\" singer reclaimed the phrase and used it as a caption for his new Variety photos, writing, \"Br… [+894 chars]"
        },
        {
            "source": {
                "id": "the-washington-post",
                "name": "The Washington Post"
            },
            "author": "Jeff Stein, Mike DeBonis, Seung Min Kim",
            "title": "Momentum builds for bipartisan ${'$'}908 billion stimulus package as more GOP senators express support - The Washington Post",
            "description": "Congress faces increasing pressure to approve relief amid a deteriorating economy.",
            "url": "https://www.washingtonpost.com/us-policy/2020/12/03/congress-economic-stimulus-coronavirus/",
            "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/2Y37HURVSUI6XFUZADJRD4J5FU.jpg&w=1440",
            "publishedAt": "2020-12-04T00:22:41Z",
            "content": "We had a good conversation, McConnell said after his discussion with Pelosi. I think were both interested in getting an outcome, both on the [spending bill] and on a coronavirus package.\r\nWhats in th… [+7017 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Sports Illustrated"
            },
            "author": "Ross Dellenger",
            "title": "Two Men and a Truck: The Last-Minute, 2,200-Mile Journey to Get BYU on the Field at Coastal Carolina - Sports Illustrated",
            "description": "How do you get undefeated No. 13 BYU on the field against fellow unbeaten No. 18 Coastal Carolina? You send two men and a giant equipment truck on a 40-hour, 2,200-mile journey.",
            "url": "https://www.si.com/college/2020/12/03/byu-equipment-truck-coastal-carolina-football-schedule",
            "urlToImage": "https://www.si.com/.image/t_share/MTc3MjYxMjk3ODAzNDY5OTg1/byu-equipment-truck.jpg",
            "publishedAt": "2020-12-04T00:20:00Z",
            "content": "A day ago, Hal Morrell never expected to be in a race against time. \r\nBut here he is.\r\nIt’s 2:30 p.m. ET Thursday and he’s seated in the passenger seat of BYU’s giant equipment truck as it hums acros… [+8620 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "New York Times"
            },
            "author": "Reid J. Epstein",
            "title": "Wisconsin’s Top Court Rejects Trump Lawsuit - The New York Times",
            "description": "The Supreme Court in the state narrowed yet another legal path for the Trump campaign as it tries to overturn his loss to Joe Biden.",
            "url": "https://www.nytimes.com/2020/12/03/us/politics/wisconsin-georgia-trump-election.html",
            "urlToImage": "https://static01.nyt.com/images/2020/12/03/us/politics/03wisconsin/03wisconsin-facebookJumbo.jpg",
            "publishedAt": "2020-12-04T00:11:00Z",
            "content": "The Trump lawsuit also argued that municipal clerks should not have been allowed to complete address forms for witnesses to absentee ballots, which the elections commission had given them permission … [+2014 chars]"
        },
        {
            "source": {
                "id": "politico",
                "name": "Politico"
            },
            "author": "Anita Kumar, Andrew Desiderio",
            "title": "Trump mulls preemptive pardons for up to 20 allies, even as Republicans balk - POLITICO",
            "description": "The clemency would be unprecedented, and some Republicans are expressing initial hesitation. But they’re not telling Trump to stop.",
            "url": "https://www.politico.com/news/2020/12/03/trump-considers-more-pardons-442727",
            "urlToImage": "https://static.politico.com/49/ef/9641242e4235958854272e93f32c/20201203-donald-trump-ap-773-2.jpg",
            "publishedAt": "2020-12-04T00:09:00Z",
            "content": "That is in a category that I think youd probably run into a lot of static, said Sen. Mike Braun (R-Ind.). Thats charting new territory, Im guessing. I dont think thats ever been attempted before.\r\nTh… [+7307 chars]"
        },
        {
            "source": {
                "id": "business-insider",
                "name": "Business Insider"
            },
            "author": "Morgan McFall-Johnsen & Aylin Woodward, Business Insider",
            "title": "Video Shows The Heartbreaking Moment The Arecibo Telescope Collapsed - ScienceAlert",
            "description": "The second-largest radio telescope in the world collapsed on Tuesday morning.",
            "url": "https://www.businessinsider.com/video-arecibo-telescope-puerto-rico-collapse-2020-12",
            "urlToImage": "https://www.sciencealert.com/images/2020-12/processed/arecibo_collapse_video_cover_pic_1024.jpg",
            "publishedAt": "2020-12-03T23:45:03Z",
            "content": "The second-largest radio telescope in the world collapsed on Tuesday morning.\r\nThe Arecibo Observatory's 900-ton platform, which sent and received radio waves and was suspended 450 feet (140 metres) … [+4741 chars]"
        },
        {
            "source": {
                "id": "cnn",
                "name": "CNN"
            },
            "author": "Dan Merica, CNN",
            "title": "CNN Exclusive: Biden says he will ask Americans to wear masks for the first 100 days he's in office - CNN",
            "description": "President-elect Joe Biden told CNN's Jake Tapper on Thursday that he will ask Americans to wear masks for his first 100 days after he takes office, in a sign of how Biden's approach to the virus will be dramatically different to President Donald Trump's respo…",
            "url": "https://www.cnn.com/2020/12/03/politics/biden-harris-interview-jake-tapper/index.html",
            "urlToImage": "https://cdn.cnn.com/cnnnext/dam/assets/201203164514-tapper-biden-harris-1203-split-super-tease.jpg",
            "publishedAt": "2020-12-03T23:45:00Z",
            "content": "(CNN)President-elect Joe Biden told CNN's Jake Tapper on Thursday that he will ask Americans to wear masks for his first 100 days after he takes office, in a sign of how Biden's approach to the virus… [+5164 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Variety"
            },
            "author": "Dave McNary",
            "title": "AMC Opposes Warner Bros.’ Plan to Stream 2021 Theatrical Releases Immediately - Variety",
            "description": "Adam Aron, CEO and president of the AMC Entertainment theater chain, is opposing Warner Bros.’ plan to simultaneously release all of its 2021 movies on HBO Max and in theaters. Aron noted Thursday afternoon that he had backed Warner’s previous strategy, unvei…",
            "url": "https://variety.com/2020/film/news/amc-warner-bros-hbo-max-1234845908/",
            "urlToImage": "https://variety.com/wp-content/uploads/2020/10/MovieTheatres224784.jpg?w=1000",
            "publishedAt": "2020-12-03T23:39:00Z",
            "content": "Adam Aron, CEO and president of the AMC Entertainment theater chain, is opposing Warner Bros.’ plan to simultaneously release all of its 2021 movies on HBO Max and in theaters.\r\nAron noted Thursday a… [+2276 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "CNET"
            },
            "author": "Leslie Katz",
            "title": "Taylor Swift's new Love Story debuts in Ryan Reynolds' Match ad starring Satan - CNET",
            "description": "Satan and the hellish 2020 look like quite the happy couple as they snap pictures together in front of a dumpster fire.",
            "url": "https://www.cnet.com/news/taylor-swift-new-love-story-debuts-in-ryan-reynolds-match-ad-starring-satan/",
            "urlToImage": "https://cnet1.cbsistatic.com/img/2OW8ZYkVEy8QnqfmUSly-WSfLmw=/1200x630/2020/12/03/3d011634-9946-4bec-b3cb-c07d5cd53908/20201202-satanmatch2020-3x2-0-1.jpg",
            "publishedAt": "2020-12-03T23:29:00Z",
            "content": "Match \r\nIf you were tasked with finding the perfect match for Satan, what traits would you look for in a potential mate? Dark, difficult and demonic might be a good start. Sounds a lot like... the ye… [+1839 chars]"
        },
        {
            "source": {
                "id": "google-news",
                "name": "Google News"
            },
            "author": null,
            "title": "North Texas bars must close, restaurants reduce capacity as COVID hospitalizations rise - Fort Worth Star-Telegram",
            "description": null,
            "url": "https://news.google.com/__i/rss/rd/articles/CBMiRGh0dHBzOi8vd3d3LnN0YXItdGVsZWdyYW0uY29tL25ld3MvY29yb25hdmlydXMvYXJ0aWNsZTI0NzU4MzExMC5odG1s0gFEaHR0cHM6Ly9hbXAuc3Rhci10ZWxlZ3JhbS5jb20vbmV3cy9jb3JvbmF2aXJ1cy9hcnRpY2xlMjQ3NTgzMTEwLmh0bWw?oc=5",
            "urlToImage": null,
            "publishedAt": "2020-12-03T23:18:00Z",
            "content": null
        },
        {
            "source": {
                "id": "the-washington-post",
                "name": "The Washington Post"
            },
            "author": "Drew Harwell, Nitasha Tiku",
            "title": "Google’s star AI ethics researcher, one of a few Black women in the field, says she was fired for a critical email - The Washington Post",
            "description": "Timnit Gebru, a star researcher who has criticized the company’s lack of diversity, emailed co-workers that she felt “constantly dehumanized.\" Her managers, she said, abruptly fired her shortly after.",
            "url": "https://www.washingtonpost.com/technology/2020/12/03/timnit-gebru-google-fired/",
            "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/OA5YUVRVUYI6XFUZADJRD4J5FU.jpg&w=1440",
            "publishedAt": "2020-12-03T22:41:20Z",
            "content": "Google officials did not respond to requests for comment. Gebru also did not respond to requests, but in an interview Thursday with Bloomberg said Googles actions represented the most fundamental sil… [+8183 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Behind the Steel Curtain"
            },
            "author": "Jeff.Hartman",
            "title": "NFL Power Rankings Week 13: Steelers lose style points but keep winning - Behind the Steel Curtain",
            "description": "After the Steelers’ red hot 11-0 start, they are at, or near, the top of the Power Rankings heading into Week 13.",
            "url": "https://www.behindthesteelcurtain.com/2020/12/3/21997382/nfl-power-rankings-week-13-the-steelers-drop-in-style-points-but-just-keep-winning-chiefs-saints",
            "urlToImage": "https://cdn.vox-cdn.com/thumbor/GCbNKA-m1VJCvYQlFI8MYbweaL0=/0x0:3000x1571/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/22139825/1289082233.jpg",
            "publishedAt": "2020-12-03T22:40:00Z",
            "content": "The Pittsburgh Steelers, along with the rest of the NFL, are coming off the twelfth week of a journey which has never been done in the history of the game. Playing games amidst a global pandemic.\r\nTh… [+4120 chars]"
        },
        {
            "source": {
                "id": "the-washington-post",
                "name": "The Washington Post"
            },
            "author": "Kareem Fahim, Miriam Berger",
            "title": "After nuclear scientist’s brazen killing, Iran is torn over a response — restraint or fury? - The Washington Post",
            "description": "The outcome could have profound implications for Biden’s plans to pursue diplomacy with Tehran.",
            "url": "https://www.washingtonpost.com/world/middle_east/iran-nuclear-scientist-fakhrizadeh/2020/12/03/0cc66752-332b-11eb-9699-00d311f13d2d_story.html",
            "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/OCGIUIRVRYI6XNM4VW3RKPIQYI.jpg&w=1440",
            "publishedAt": "2020-12-03T22:39:00Z",
            "content": "The same day, however, previously unreleased audio was aired with the scientist purportedly questioning the utility of negotiations with the United States. America cant be compromised with, Fakhrizad… [+8928 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Investor's Business Daily"
            },
            "author": "Investor's Business Daily",
            "title": "Dow Jones Futures: Pfizer Coronavirus Vaccine Rollout Woes Stall Stock Market Rally; DocuSign Earnings Strong - Investor's Business Daily",
            "description": "Dow Jones futures: Thursday's stock market rally stalled on Pfizer coronavirus vaccine rollout woes. DocuSign earnings pushed DOCU stock higher.",
            "url": "https://www.investors.com/market-trend/stock-market-today/dow-jones-futures-pfizer-coronavirus-vaccine-rollout-woes-stall-stock-market-rally-docusign-earnings/",
            "urlToImage": "https://www.investors.com/wp-content/uploads/2020/06/Stock-coronavirus-21-adobe.jpg",
            "publishedAt": "2020-12-03T22:32:00Z",
            "content": "Dow Jones futures tilted higher late Thursday, along with S&amp;P 500 futures and Nasdaq futures. The stock market rally turned mixed Wednesday on an afternoon report that Pfizer (PFE) and BioNTech (… [+6232 chars]"
        }
    ]
}"""
}