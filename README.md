## to install / update:

```
mkdir -p ~/.local/lib ~/.local/bin
cp /home/NAME/IdeaProjects/netstat/out/artifacts/netstat_jar/netstat.jar ~/.local/lib/netstat.jar

cat > ~/.local/bin/myip << 'EOF'
#!/bin/sh
exec java -jar "$HOME/.local/lib/netstat.jar" "$@"
EOF
chmod +x ~/.local/bin/myip
```
