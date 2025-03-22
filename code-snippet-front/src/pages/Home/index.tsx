import { Input } from "@/components/ui/input";
import { ModeToggle } from "@/components/mode-toggle";
import { CardBody, CardContainer, CardItem } from "@/components/ui/3d-card";
import {
  CodeSnippetControllerService,
  CodeSnippetVO,
  CopyLogControllerService,
} from "@/api-client/generated";
import { useEffect, useRef, useState } from "react";
import { CodeBlock } from "@/components/ui/code-block";
import { Button } from "@/components/ui/button";
import { copyTextToClipboard } from "@/lib/utils";
import { Separator } from "@/components/ui/separator";

const HomePage = () => {
  const [codeSnippetList, setCodeSnippetList] = useState<CodeSnippetVO[]>([]);
  const queryParam = useRef<CodeSnippetVO>({
    category: "",
    content: "",
    description: "",
  });
  const [, setLoading] = useState(false);
  const loadCodeSnippetList = async () => {
    try {
      setLoading(true);
      const res = await CodeSnippetControllerService.list({
        ...queryParam.current,
      });
      if (res.code === 0 && res.data) {
        setCodeSnippetList(res.data);
      }
    } finally {
      setLoading(false);
    }
  };

  const copyHandler = async (codeSnippet: CodeSnippetVO) => {
    try {
      if (codeSnippet.content && codeSnippet.id) {
        const res = await copyTextToClipboard(codeSnippet.content);
        if (res) {
          await CopyLogControllerService.add(codeSnippet.id);
        }
      }
    } catch (error) {
      console.error("复制失败", error);
    } finally {
      loadCodeSnippetList();
    }
  };

  useEffect(() => {
    loadCodeSnippetList();
  }, []);

  return (
    <>
      {/* 修改头部容器样式 */}
      <div className="fixed w-full top-1 z-50 p-4 mx-auto  flex flex-col items-center">
        <h1 className="text-center text-5xl font-bold text-emerald-500 mb-4">
          PG Thinker's Code Snippets
        </h1>
        <div className="flex justify-center w-full">
          <div className="relative w-100 mr-2">
            <Input
              className="w-full"
              placeholder="请输入标题、代码内容、标签或描述..."
              onChange={(e) => {
                queryParam.current = {
                  title: e.target.value,
                  content: e.target.value,
                  description: e.target.value,
                };
                loadCodeSnippetList();
              }}
            />
            <span
              className="cursor-pointer absolute inset-y-0 right-0 flex items-center pr-2"
              onClick={() => {
                loadCodeSnippetList();
              }}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                className="h-5 w-5 text-gray-400"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                />
              </svg>
            </span>
          </div>
          <ModeToggle />
        </div>
      </div>

      {/* 主要内容 */}
      <div className="mt-15"></div>

      {/* 卡片 */}
      <div className="w-full h-screen pt-6 px-4">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-3 gap-7 items-start">
          {codeSnippetList.map((item) => (
            <CardContainer className="inter-var" key={item.id}>
              <CardBody className="relative group/card border border-black/[0.2] dark:border-white/[0.2] bg-gray-50 dark:bg-black shadow-md dark:hover:shadow-2xl dark:hover:shadow-emerald-500/[0.1] rounded-xl p-6 flex flex-col min-h-[800px]">
                {/* 标题 */}
                <CardItem
                  translateZ="50"
                  className="text-xl font-bold text-neutral-600 dark:text-white"
                >
                  标题：{item.title}
                </CardItem>
                {/* 分类 */}
                <CardItem as="div" translateZ="30" className="mt-2">
                  分类：
                  <span className="px-2 py-1 text-xs font-bold text-white bg-blue-500 rounded-full">
                    {item.category}
                  </span>
                </CardItem>
                <CardItem
                  as="div"
                  translateZ="30"
                  className="w-full justify-center"
                >
                  <Separator className="m-4" />
                </CardItem>
                {/* 描述 */}
                <CardItem
                  as="div"
                  translateZ="60"
                  className="text-neutral-500 text-sm mt-4 dark:text-neutral-300"
                >
                  {item.description}
                </CardItem>

                {/* 代码块，固定高度 + 滚动 */}
                <CardItem as="div" className="w-full mt-3 h-full">
                  <CodeBlock
                    language={item.language ?? ""}
                    highlightLines={[9, 13, 14, 18]}
                    code={item.content ?? ""}
                    filename="demo"
                  />
                </CardItem>
                <CardItem
                  as="div"
                  translateZ="30"
                  className="w-full justify-center"
                >
                  <Separator className="m-4" />
                </CardItem>
                {/* 操作区域 */}
                <div className="flex justify-between items-center mt-auto p-2 ">
                  <CardItem
                    translateZ={20}
                    as="text"
                    className="px-4 py-1 rounded-full text-sm font-bold"
                  >
                    复制量：{item.copyCnt}
                  </CardItem>
                  <CardItem translateZ={20} as="div">
                    <Button
                      onClick={() => copyHandler(item)}
                      className="px-4 py-2 rounded-full bg-green-500 text-white text-sm font-bold hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-opacity-50 shadow-md cursor-pointer"
                    >
                      复制
                    </Button>
                  </CardItem>
                </div>
              </CardBody>
            </CardContainer>
          ))}
        </div>
      </div>

      <div className="mt-30"></div>
    </>
  );
};

export default HomePage;
