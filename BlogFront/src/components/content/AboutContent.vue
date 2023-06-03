<template>
  <div class="article-content layout-content">
    <Row>
      <Column :xs="24" :sm="24" :md="24" :lg="24" :xl="17">
        <div class="layout-left">
          <div class="about-content" style="padding:0;">
            <ul class="myblog">
              <div class="intro">
                <h5>Blog Introduction</h5>
                <div class="intro_detail">
                  <div style="text-align: left">
                    1: A blog system base on springBoot and Vue.
                    <span class="wb_url">
                      <a href="https://github.com/0utsiderZhong/BlogServer"
                         target="_blank">
                        Go to github
                      </a>
                    </span><br>
                    2: Domain: www.clockblog.life
                    <span class="wb_url">
                      <a href="https://account.godaddy.com"
                         target="_blank">
                        How to register a domain
                      </a>
                    </span><br>
                    3: Server Configuration: Google Cloud ECS 2C4G
                    <span class="wb_url">
                    <a href="https://console.cloud.google.com/"
                       target="_blank">
                      How to get a server
                    </a>
                    </span><br>
                  </div>
                </div>
                <h5>Goal</h5>
                <div class="intro_detail">
                  <div>
                    &nbsp;&nbsp;&nbsp;&nbsp;When I built my blog site, I had several purposes in mind.<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;To share my thoughts and knowledge: As a person with thoughts and insights,
                    I want to share my thoughts, experiences and knowledge with readers through the blog site to provide
                    them with valuable information and inspiration. I will share my insights and experiences, discuss
                    some interesting topics and issues, as well as share some stories of personal growth and progress,
                    hoping to bring some help and inspiration to readers.
                    <hr>
                    <br>
                    &nbsp;&nbsp; &nbsp;&nbsp;Interacting and communicating with readers: Building a blog site is also
                    about connecting and interacting with readers. I hope to establish good interaction and
                    communication with readers through the blog site, understand their ideas and opinions, receive their
                    feedback and suggestions, so as to continuously improve my writing and expression skills. In the
                    blog site, I will set up a comment section where readers can leave comments, reply and discuss,
                    hoping to establish a positive, friendly and supportive communication environment.
                    <hr>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;Record my growth and progress: Building a blog site also allows me to record
                    my growth and progress. Through the blog site, I can record my learning and growth, share my
                    thoughts and insights, and summarize and reflect on my growth and progress. This will not only help
                    me understand myself better, but also allow readers to benefit from my experience and grow and
                    progress together.
                    <hr>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;Displaying my personal brand and expanding my influence: Building a blog
                    site is also a way to display my personal brand and expand my influence. Through a blog site, I can
                    showcase my expertise, abilities and values to my readers and bring a positive impact to my career
                    and relationships. In addition, through the blog site, I can expand my influence, attract more
                    readers and followers, and build my social network, thus laying the foundation for future
                    development.
                    <hr>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;To sum up, my purpose of building a blog site is multi-faceted, both to
                    bring value to readers and to contribute to my own growth and development. I hope to be able to
                    connect and interact with more people through the blog site and grow and progress together.
                    <hr>
                    <br>
                  </div>
                </div>
              </div>
            </ul>
            <span class="ly_button">
              <a href="/leaveComment" target="_blank">Leave a Message To me</a>
            </span>
            <p class="ab_dubai">
            <div class="intro">
              <h5>Resume:</h5>
              <div class="intro_detail">
                <div>
                  Name: Clock<br>
                  Gender: male<br>
                  Age: 23<br>
                  From: Changsha,Hunan<br>
                  Profession: Software Engineer<br>
                  QQ: 2783241916<br><br>
                  <span>Something About Myself</span><br>
                  1: 2000 Born in Changsha,hunan<br>
                  2: 2006-2008 Study at Changsha,hunan<br>
                  3: 2008-2018 Study at Bao'an District, Shenzhen<br>
                  4: 2018-2022 South China Agricultural University<br>
                  5: 2022.2-2022-6 Used to be an intern at VipShop<br>
                  6: 2022.7-2023-2 Used to be an SoftWare Engineer at VipShop Infrastructure <br>
                </div>
              </div>
              <h5>My Dream:</h5>
              <div class="intro_detail">
                <div>
                  I hope that one day I can find my dream
                </div>
              </div>
              <h5>Technology:</h5>
              <div class="intro_detail">
                <div>
                  1: Java, Python, Golang<br>
                  2: Spring Framework, Hibernate ORM, JPA, Mybatis<br>
                  3: MySQL or Oracle Database, Redis Cache<br>
                  4: Apache Tomcat, Apache Maven, Git or SVN for version control <br>
                  5: Apache Kafka for message queuing<br>
                  6: Linux, Shell, Docker or Kubernetes for containerization and deployment. <br>
                  7: HTML, CSS, JavaScript and VUE for web front-end development<br>
                </div>
              </div>
              <h5 id="project">Project:</h5>
              <div class="intro_detail">
                <div>
                  1: This Blog system
                  <span class="wb_url"><a href="https://github.com/0utsiderZhong/BlogServer">Detail</a></span><br>
                  2: Automatic coloring for anime sketch with Pix2PixHD
                  <span class="wb_url"><a href="ieeexplore.ieee.org/abstract/document/9731216">Detail</a></span><br>
                  3: Enterprise-grade Jupyterhub service
                  <span class="wb_url"><a
                      href="https://jupyterhub.readthedocs.io/en/stable/getting-started/config-basics.html#run-the-proxy-separately">Detail</a></span><br>
                  4: Tensorboard Service
                  <span class="wb_url"><a href="https://github.com/tensorflow/tensorboard">Detail</a></span><br>
                  4: GaeaDNS DNS server<br>
                </div>
              </div>
              <h5>Hobby</h5>
              <div class="intro_detail">
                <div>
                  1: Music, especially Xu Wei's music<br>
                  2: Sports, such as badminton, swimming, riding, fitness...<br>
                  3: Traveling, i hope i can travel all over the world one day<br>
                  4: LOL, but just Smash Mode <br>
                  5: Watching American TV series <br>
                  6: Eating
                </div>
              </div>
            </div>
          </div>
        </div>
      </Column>
      <Column :xs="24" :sm="24" :md="24" :lg="24" :xl="7">
        <div class="layout-right">
          <Recommend/>
          <Hot style="margin-top:15px;"/>
          <TagWall style="margin-top:15px;"/>
          <FriendLinks style="margin-top:15px;"/>
        </div>
      </Column>
    </Row>
  </div>
</template>

<script>
import Recommend from "../../views/Recommend";
import TagWall from "../../views/TagWall";
import Hot from "../../views/Hot";
import FriendLinks from "../../views/FriendLinks";

export default {
  name: "AboutContent",
  components: {
    Recommend, TagWall, Hot, FriendLinks
  },
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "../../common/stylus/theme.styl";

.about-content {
  line-height: 25px;
  margin-bottom: 30px;
  padding: 20px;
}

.intro {
  float: left;
  font-size: 20px;
}

.intro h5 {
  font-size: 30px;
  color #222222
  width: 60%;
  font-weight: bold;
  margin-top: 10px;
  float: left;
}

.intro hr {
  border: 0.5px dashed #ccc;
}

.intro_detail div {
  margin: 10px 0;
  float: left;
  padding: 10px 20px 10px 20px;
  color: #555;
  border-left: 4px solid #0cc;
}

.intro_detail .wb_url {
  margin: 0 10px;
}

.intro_detail .wb_url a {
  color: #1CA368;
}

.intro span {
  font-weight: bold;
  font-size 25px
  color #222222;
}

.ab_box h3 {
  margin: 10px 0 10px;
  font-size: 18px;
}

.ab_box p {
  color: #666;
  margin-bottom: 5px
}

.avatar_pic img {
  width: 100%;
  border-radius: 50%;
}

.ab_dubai {
  margin-bottom: 15px;
  display: inline-block;
}

.ly_button {
  float: right;
  width: 200px;
  background: #000;
  text-align: center;
  border-radius: 3px;
  line-height: 30px
}

.ly_button a {
  color: #FFF
}

</style>
