FROM tomcat:9.0.119-jre25-temurin-noble:11
WORKDIR /allblue
COPY index.html .
COPY home.html .
COPY packages.html .
COPY contact.html .
COPY ContactServlets.java .
COPY LoginServlets.java .
EXPOSE 5000
CMD [ "tomcat:9.0.119-jre25-temurin-noble" ]
