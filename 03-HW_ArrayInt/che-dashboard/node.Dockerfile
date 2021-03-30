FROM node:12

#RUN npm install -g yarn

COPY . .

EXPOSE 3000

RUN yarn install --network-timeout 600000
#RUN yarn compile

# RUN yarn start", "--env.server=https://che.openshift.io" ]

#RUN yarn start --env.server=https://che.openshift.io 
RUN yarn run serve:prod