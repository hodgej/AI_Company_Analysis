web_templates.json Documentation:

Each URL we scrape gets an object
 - Data insertion type : 1 if insert stock ticker, 0 if insert company name
 - url_template: url in which the data from data insertion type is inserted (company name/stock sym)
 - elements: all elements to be scraped (in full XPATH format)
     - Each element is a key associated with context to the AI
     - Thus the final format will be [element info] is [context for the AI]
     - No limit on number of elements you can scrape here.

