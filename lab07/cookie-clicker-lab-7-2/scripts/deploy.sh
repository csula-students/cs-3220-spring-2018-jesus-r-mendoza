#! /bin/bash

USERNAME=$1
REMOTE_URL=$USERNAME@cs3.calstatela.edu
WEB_PATH="/home/$USERNAME/www"
APPLICATION_PATH="$REMOTE_URL:$WEB_PATH"

if [[ -z "$USERNAME" ]]; then
	echo "Missing username argument. Pass username like './scripts/deploy.sh cs3220stu01'"
	exit 1
fi

echo "deploying to ${APPLICATION_PATH}"

# deploying the compiled class files
scp -r build/classes/java/main/* "$APPLICATION_PATH/WEB-INF/classes/"
# deploy libraries
scp -r lib/* "$APPLICATION_PATH/WEB-INF/lib/"
# deploy static contents (including WEB-INF/web.xml)
scp -r src/main/webapp/* "$APPLICATION_PATH/"

# manually touch web.xml
echo "touching web.xml to trigger web application restart..."
ssh "$REMOTE_URL" touch "$WEB_PATH/WEB-INF/web.xml"
