# stream-url-handler
Implement iOS vlc-x-callback on Windows and Linux, with subtitle url support, but use mpv to open the video instead of vlc (faster and easier!)

## Installation

Support Ubuntu 22.04 and Windows (both tested)

Download .deb or .exe from release tab and install. (reboot required!)

## Introduction

In iOS VLC, we can use `vlc-x-callback://` protocol to open VLC and play a specific video (with remote subtitle file)

But in Windows and Linux, no such functionality exists.

In addition, PC version of VLC only supports local subtitle files

Therefore, I decided to write a program that can parse the `vlc-x-callback://` url and open the video directly in mpv (which supports remote subtitle file) when we try to open the link.

This is useful when you try to create a streaming service that relies on external player to play videos

The program accepts two parameters in the link: `url=<video url>` and `sub=<subtitle url>`. Example in [url.txt](https://github.com/yzu1103309/stream-url-handler/blob/main/url.txt)

## Testing

To test if the program works after installation, you can use the link in [url.txt](https://github.com/yzu1103309/stream-url-handler/blob/main/url.txt) to test

just simply paste the url to your browser, or type this command in the terminal (cmd):

```
stream-url-handler <url>
```
(replace `url` with the link in the file)
