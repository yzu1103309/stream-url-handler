#include <iostream>
#include <string>
#include <unistd.h>
using namespace std;

int main(int argc, char * argv[])
{
    if(argc <= 1)
    {
        cerr << "No URL Input!"<<endl;
        exit(EXIT_FAILURE);
    }

    string url(argv[1]);

    size_t pos;

    // url format: vlc-x-callback://stream?url=https://...mp4&sub=https://...srt
    pos = url.find("url=");
    if(pos != string::npos)
    {
        size_t start_vd, vd_length;
        size_t start_sub, sub_length;
        bool sub_found = false;

        start_vd = pos + 4;

        // look for sub
        pos = url.find("sub=");
        if(pos != string::npos)
        {
            vd_length = pos - 1 - start_vd;
            start_sub = pos + 4;
            sub_length = url.size() - start_sub;
            sub_found = true;
        }
        else
        {
            vd_length = url.size() - start_vd;
        }
        string vd = url.substr(start_vd, vd_length);
        if(sub_found)
        {
            string sub = url.substr(start_sub, sub_length);
            sub = string("--sub-file=")+sub;
            execlp("mpv", "mpv", vd.c_str(), sub.c_str(), "--fullscreen", NULL);
        }
        else
        {
            execlp("mpv", "mpv", vd.c_str(), "--fullscreen", NULL);
        }
    }

    return 0;
}

