package com.audio.util;

/**
 * @class descptioon	:
 * @author 				:gaoxiang
 * @date 				:2010-3-22
 **/

public class UBB
{
    
    public UBB()
    {
        UBBReg = new String[24];
        HTMLTags = new String[24];
        UBBReg[0] = "\\[img\\](.+?)\\[/img\\]";
        HTMLTags[0] = "<a href=$1 target=_blank><img src=$1 border=0 alt='\u70B9\u51FB\u5728\u65B0\u7A97\u53E3\u6D4F\u89C8\u56FE\u7247' onload=\"javascript:if(this.width > 600)this.width = 600\" galleryimg='no'></a>";
        UBBReg[1] = "\\[wmv=*([0-9]*),*([0-9]*),*([0-9]*)\\](.*?)\\[/wmv\\]";
        HTMLTags[1] = "<br><object align=middle classid=clsid:22d6f312-b0f6-11d0-94ab-0080c74c7e95 class=object id=mediaplayer width=$1 height=$2 ><param name=showstatusbar value=-1><param name=filename value=$4><param name=AutoStart value=$3><embed type=application/x-oleobject codebase=http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#version=5,1,52,701 flename=mp src=$4  width=$1 height=$2 autostart=$3 ></embed></object><br>";
        UBBReg[2] = "\\[rm=*([0-9]*),*([0-9]*),*([0-9]*)\\](.*?)\\[/rm\\]";
        HTMLTags[2] = "<br><object classid=clsid:cfcdaa03-8be4-11cf-b84b-0020afbbccfa class=object id=raocx width=$1 height=$2><param name=src value=$4><param name=console value=clip1><param name=controls value=imagewindow><param name=autostart value=$3></object><br><object classid=clsid:cfcdaa03-8be4-11cf-b84b-0020afbbccfa height=32 id=video2 width=$1><param name=src value=$4><param name=autostart value=$3><param name=controls value=controlpanel><param name=console value=clip1></object><br>";
        UBBReg[3] = "\\[flash=*([0-9]*),*([0-9]*)\\](.*?)\\[/flash\\]";
        HTMLTags[3] = "<a href=$3 target=_blank>[\u5168\u5C4F\u6B23\u8D4F]</a><br><object codebase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0 classid=clsid:d27cdb6e-ae6d-11cf-96b8-444553540000 width=$1 height=$2><param name=movie value=$3><param name=quality value=high><embed src=$3 quality=high pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width=$1 height=$2>$3</embed></object>";
        UBBReg[4] = "\\[upload=gif\\](.*?)\\[/upload\\]";
        HTMLTags[4] = "<br>\u6B64\u4E3B\u9898\u76F8\u5173\u56FE\u7247\u5982\u4E0B\uFF1A<br><a href=$1 target=_blank><img src=$1 border=0 alt=\u6309\u6B64\u5728\u65B0\u7A97\u53E3\u6D4F\u89C8\u56FE\u7247 onload=\"javascript:if(this.width > 600)this.width = 600\"></a>";
        UBBReg[5] = "\\[upload=jpg\\](.*?)\\[/upload\\]";
        HTMLTags[5] = "<br>\u6B64\u4E3B\u9898\u76F8\u5173\u56FE\u7247\u5982\u4E0B\uFF1A<br><a href=$1 target=_blank><img src=$1 border=0 alt=\u6309\u6B64\u5728\u65B0\u7A97\u53E3\u6D4F\u89C8\u56FE\u7247 onload=\"javascript:if(this.width > 600)this.width = 600\"></a>";
        UBBReg[6] = "\\[upload=bmp\\](.*?)\\[/upload\\]";
        HTMLTags[6] = "<br>\u6B64\u4E3B\u9898\u76F8\u5173\u56FE\u7247\u5982\u4E0B\uFF1A<br><a href=$1 target=_blank><img src=$1 border=0 alt=\u6309\u6B64\u5728\u65B0\u7A97\u53E3\u6D4F\u89C8\u56FE\u7247 onload=\"javascript:if(this.width > 600)this.width = 600\"></a>";
        UBBReg[7] = "\\[upload=(.*?)\\](.*?)\\[/upload\\]";
        HTMLTags[7] = "<br><img src=images/attachicons/$1.gif border=0><a href=$2 target=_blnk>\u70B9\u51FB\u6D4F\u89C8\u8BE5\u6587\u4EF6</a>";
        UBBReg[8] = "\\[url\\](.*?)\\[/url\\]";
        HTMLTags[8] = "<a href=$1 target=_blank title=\u5728\u65B0\u7A97\u53E3\u6253\u5F00\"$1\">$1</a>";
        UBBReg[9] = "\\[url=(.*?)\\](.*?)\\[/url\\]";
        HTMLTags[9] = "<a href=$1 target=_blank title=\u5728\u65B0\u7A97\u53E3\u6253\u5F00\"$2\">$2</a>";
        UBBReg[10] = "\\[color=(.*?)\\](.*?)\\[/color\\]";
        HTMLTags[10] = "<font color=$1>$2</font>";
        UBBReg[11] = "\\[font=(.*?)\\](.*?)\\[/font\\]";
        HTMLTags[11] = "<font face=$1>$2</font>";
        UBBReg[12] = "\\[align=(left|right|center)\\](.*?)\\[/align\\]";
        HTMLTags[12] = "<div align=$1>$2</div>";
        UBBReg[13] = "\\[quote\\](.*?)\\[/quote\\]";
        HTMLTags[13] = "<div class='quoteBox'>$1</div>";
        UBBReg[14] = "\\[fly\\](.*?)\\[/fly\\]";
        HTMLTags[14] = "<marquee width='90%' behavior='alternate' scrollamount='3'>$1</marquee>";
        UBBReg[15] = "\\[move=(down|up|left|right)\\](.*?)\\[/move\\]";
        HTMLTags[15] = "<marquee direction=$1 scrollamount='3'>$2</marquee>";
        UBBReg[16] = "\\[glow=*([#0-9a-zA-Z]*),*([0-9]*)\\](.*?)\\[/glow\\]";
        HTMLTags[16] = "<table style=\"filter:glow(color=$1, strength=$2)\"><tr><td>$3</td></tr></table>";
        UBBReg[17] = "\\[i\\](.*?)\\[/i\\]";
        HTMLTags[17] = "<i>$1</i>";
        UBBReg[18] = "\\[u\\](.*?)\\[/u\\]";
        HTMLTags[18] = "<u>$1</u>";
        UBBReg[19] = "\\[b\\](.*?)\\[/b\\]";
        HTMLTags[19] = "<b>$1</b>";
        UBBReg[20] = "\\[size=*([1-7]*)\\](.*?)\\[/size\\]";
        HTMLTags[20] = "<font size=$1>$2</font>";
        UBBReg[21] = "\\[:(.*?)\\]";
        HTMLTags[21] = "<img src=$1 />";
        UBBReg[22] = "\\[qtitle\\](.*?)\\[/qtitle\\]";
        HTMLTags[22] = "<div class='quoteBoxTitle'>$1</div>";
        UBBReg[23] = "\\[qtext\\](.*?)\\[/qtext\\]";
        HTMLTags[23] = "<div class='quoteBoxText'>$1</div>";
        
    }
    
    public String unUBB(String source)
    {
        if (source == null || "null".equals(source))
            return source;
        source = filterHtml(source);
        for (int i = 0; i < UBBReg.length; i++)
            if (i == 13)
                source = source.replaceAll(UBBReg[13], HTMLTags[13]);
            else
                source = source.replaceAll(UBBReg[i], HTMLTags[i]);
        return source;
    }
    
    public static String filterHtml(String s)
    {
        if (null == s)
        {
            return "";
        }
        char c[] = s.toCharArray();
        StringBuffer buf = new StringBuffer();
        int i = 0;
        for (int size = c.length; i < size; i++)
        {
            char ch = c[i];
            if (ch == '"')
                buf.append("");
            else if (ch == '&')
                buf.append("");
            else if (ch == '<')
                buf.append("");
            else if (ch == '>')
                buf.append("");
            else if (ch == '\n')
                buf.append("");
            else if (ch == '\'')
                buf.append("");
            else if (ch == '•')
                buf.append("·");
            else if (ch == '\r')
            {
            }
            else
                buf.append(ch);
        }
        
        c = (char[]) null;
        return buf.toString();
    }
    
    
    public static String filterHtmlByContent(String s)
    {
        if (null == s)
        {
            return "";
        }
        char c[] = s.toCharArray();
        StringBuffer buf = new StringBuffer();
        int i = 0;
        for (int size = c.length; i < size; i++)
        {
            char ch = c[i];
            if (ch == '\'')
                buf.append("");
            else if (ch == '&')
                buf.append("");
            else if (ch == '\n')
                buf.append("");
            else if (ch == '\'')
                buf.append("");
            else if (ch == '\r')
            {
            }
            else
                buf.append(ch);
        }
        
        c = (char[]) null;
        return buf.toString();
    }
    
    private String UBBReg[];
    
    private String HTMLTags[];
}
