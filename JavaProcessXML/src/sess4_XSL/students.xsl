<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : students.xsl
    Created on : 31 July 2017, 14:51
    Author     : c.muturi
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Java Processing XML - Student Details Page</title>
            </head>
            <body>
                <table style="margin-left:auto;margin-right:auto;" border="2">
                    <tr>
                        <!--Specify the table headings-->
                        <th>Registration No.</th>
                        <th>Student&quot; Names</th>
                        <th>Course Enrolled</th>
                        <th>Email Address</th>
                    </tr>
                    
                    <!-- Loop through each student's details using a for-each loop-->
                    <xsl:for-each select="/students/student">
                        <tr>
                            <td><xsl:value-of select="regno"/></td>
                            <td><xsl:value-of select="names"/></td>
                            <td><xsl:value-of select="course"/></td>
                            <td><xsl:value-of select="email"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
