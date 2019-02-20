#!/bin/bash

mkdir -p ~/.bin
mkdir -p ~/.tmp
pushd ~/.tmp

wget -O erl.deb https://bit.ly/2E4UUxT
wget -O rmq.tar.xz https://bit.ly/2SnHcyB

dpkg -x erl.deb ./erl
cp -R ./erl/usr/lib/erlang ~/.bin/erlang

tar xfv rmq.tar.xz
mv rabbitmq_server-3.7.11 ~/.bin/rmq-server

popd

cp ./files/erl ~/.bin/erlang/erts-10.2.3/bin/erl
chmod +x ~/.bin/erlang/erts-10.2.3/bin/erl
