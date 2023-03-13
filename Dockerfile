FROM maven:3.8.4-openjdk-17
COPY . /app
WORKDIR /app

# install google chrome
ARG CHROME_VERSION=111.0.5563.64-1
ADD google-chrome.repo /etc/yum.repos.d/google-chrome.repo
RUN microdnf install -y google-chrome-stable-$CHROME_VERSION \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

# install chromedriver
ARG CHROME_DRIVER_VERSION=111.0.5563.64
RUN microdnf install -y unzip \
	&& curl -s -o /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
	&& unzip /tmp/chromedriver.zip -d /opt \
	&& rm /tmp/chromedriver.zip \
	&& mv /opt/chromedriver /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& chmod 755 /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& ln -s /opt/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

