#!/bin/sh
set -e

REPO="jteuhola/myip"
NAME="myip"
LIB="$HOME/.local/lib"
BIN="$HOME/.local/bin"

command -v java >/dev/null 2>&1 || { echo "Java 17+ is required." >&2; exit 1; }

mkdir -p "$LIB" "$BIN"
curl -fsSL -o "$LIB/$NAME.jar" \
  "https://github.com/$REPO/releases/latest/download/myip.jar"

cat > "$BIN/$NAME" <<EOF
#!/bin/sh
exec java -jar "$LIB/$NAME.jar" "\$@"
EOF
chmod +x "$BIN/$NAME"

echo "Installed $NAME to $BIN/$NAME"
echo "Make sure $BIN is on your PATH."