FROM ubuntu:latest
ENV PATH=$PATH:/jdk-15.0.2/bin
ENV JAVA_HOME=/jdk-15.0.2
ENV USE_GKE_GCLOUD_AUTH_PLUGIN=True
RUN apt update
RUN apt install -y sudo
RUN apt-get -y install curl
RUN sudo apt-get install -y apt-transport-https ca-certificates gnupg
RUN echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
RUN curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key --keyring /usr/share/keyrings/cloud.google.gpg add -
RUN echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] http://packages.cloud.google.com/apt cloud-sdk main" | tee -a /etc/apt/sources.list.d/google-cloud-sdk.list && curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key --keyring /usr/share/keyrings/cloud.google.gpg  add - && apt-get update -y && apt-get install google-cloud-cli -y
RUN sudo apt-get -y install google-cloud-cli
RUN sudo apt-get -y install kubectl
RUN sudo apt-get -y install google-cloud-sdk-gke-gcloud-auth-plugin
RUN curl https://download.java.net/java/GA/jdk15.0.2/0d1cfde4252546c6931946de8db48ee2/7/GPL/openjdk-15.0.2_linux-x64_bin.tar.gz -o openjdk-15.0.2_linux-x64_bin.tar.gz
RUN tar -xzf openjdk-15.0.2_linux-x64_bin.tar.gz && rm -rf openjdk-15.0.2_linux-x64_bin.tar.gz
RUN java --version && sleep 5s
RUN sudo apt-get -y install maven
RUN mvn -v && sleep 10s
RUN java --version && sleep 5s
